package com.tpadev

import com.fasterxml.jackson.annotation.JsonProperty
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.LoadResponse.Companion.addActors
import com.lagradost.cloudstream3.LoadResponse.Companion.addTrailer
import com.lagradost.cloudstream3.extractors.helper.AesHelper
import com.lagradost.cloudstream3.network.CloudflareKiller
import com.lagradost.cloudstream3.utils.*
import org.jsoup.nodes.Element
import java.net.URI
import java.util.Base64
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll



class XilidProvider : MainAPI() {
    //override var mainUrl = listOf(
	//	'h','t','t','p','s',':','/','/',
	//	'i','d','l','i','x','i','a','n','.','c','o','m',).joinToString("")

	override var mainUrl = listOf(
		'h','t','t','p','s',':','/','/',
		't','v','7','.','i','d','l','i','x','k','u','.','c','o','m').joinToString("")

	//override var mainUrl = "https://"
	private var directUrl = mainUrl
    override var name = "Xilid"
    override val hasMainPage = true
    override var lang = "id"
    override val hasDownloadSupport = true
    //private val cloudflareKiller by lazy { CloudflareKiller() }
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime,
        TvType.AsianDrama
    )


    init {
        // this runs once when the provider is created
        try {
            val client = OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .build()

            val request = Request.Builder()
                .url(mainUrl)
                .build()

            client.newCall(request).execute().use { response ->
                val finalUrl = response.request.url.toString()
                mainUrl = finalUrl
                directUrl = finalUrl
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override val mainPage = mainPageOf(
        "$mainUrl/" to "Featured",
        "$mainUrl/movie/page/" to "Latest Movie",		
        "$mainUrl/network/netflix/page/" to "Netflix",
        "$mainUrl/network/amazon/page/" to "Amazon",
        "$mainUrl/network/apple-tv/page/" to "AppleTV+",
        "$mainUrl/network/disney/page/" to "Disney+",
        "$mainUrl/network/hbo/page/" to "HBO",
        "$mainUrl/genre/drama-korea/page/" to "Drama Korea",
        "$mainUrl/genre/drama-jepang/page/" to "Drama Japan",
        "$mainUrl/genre/drama-china/page/" to "Drama China",
        "$mainUrl/genre/drama-thai/page/" to "Drama Thailand",

//        "$mainUrl/trending/page/?get=movies" to "Trending Movies",
//        "$mainUrl/trending/page/?get=tv" to "Trending TV Series",
//        "$mainUrl/trending/page/?get=movies" to "Trending Movies",
//        "$mainUrl/trending/page/?get=tv" to "Trending TV Series",
//        "$mainUrl/tvseries/page/" to "TV Series Terbaru",
//        "$mainUrl/genre/anime/page/" to "Anime",

    )

    private fun getBaseUrl(url: String): String {
        return URI(url).let {
            "${it.scheme}://${it.host}"
        }
    }

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {
        val url = request.data.split("?")
        val nonPaged = request.name == "Featured" && page <= 1
        val req = if (nonPaged) {
            app.get(request.data)
        } else {
            app.get("${url.first()}$page/?${url.lastOrNull()}")
        }
        mainUrl = getBaseUrl(req.url)
        val document = req.document
        val home = (if (nonPaged) {
            document.select("div.items.featured article")
        } else {
            document.select("div.items.full article, div#archive-content article")
        }).mapNotNull {
            it.toSearchResult()
        }
        return newHomePageResponse(request.name, home)
    }

    private fun getProperLink(uri: String): String {
        return when {
            uri.contains("/episode/") -> {
                var title = uri.substringAfter("$mainUrl/episode/")
                title = Regex("(.+?)-season").find(title)?.groupValues?.get(1).toString()
                "$mainUrl/tvseries/$title"
            }

            uri.contains("/season/") -> {
                var title = uri.substringAfter("$mainUrl/season/")
                title = Regex("(.+?)-season").find(title)?.groupValues?.get(1).toString()
                "$mainUrl/tvseries/$title"
            }

            else -> {
                uri
            }
        }
    }

    private fun Element.toSearchResult(): SearchResponse {
        val title = this.selectFirst("h3 > a")!!.text().replace(Regex("\\(\\d{4}\\)"), "").trim()
        val href = getProperLink(this.selectFirst("h3 > a")!!.attr("href"))
        val posterUrl = this.select("div.poster > img").attr("src").toString().replace("w185", "w500")
        val quality = getQualityFromString(this.select("span.quality").text())
        return newMovieSearchResponse(title, href, TvType.Movie) {
            this.posterUrl = posterUrl
            this.quality = quality
        }

    }


    override suspend fun search(query: String): List<SearchResponse> {
        val req = app.get("$mainUrl/search/$query")
        mainUrl = getBaseUrl(req.url)
        val document = req.document
        return document.select("div.result-item").map {
            val title =
                it.selectFirst("div.title > a")!!.text().replace(Regex("\\(\\d{4}\\)"), "").trim()
            val href = getProperLink(it.selectFirst("div.title > a")!!.attr("href"))
            val posterUrl = it.selectFirst("img")!!.attr("src").toString().replace("w185", "w500")
            newMovieSearchResponse(title, href, TvType.TvSeries) {
                this.posterUrl = posterUrl
            }
        }
    }

    override suspend fun load(url: String): LoadResponse {
        val request = app.get(url)
        directUrl = getBaseUrl(request.url)
        val document = request.document
        val title =
            document.selectFirst("div.data > h1")?.text()?.replace(Regex("\\(\\d{4}\\)"), "")
                ?.trim().toString()
        //val poster = document.select("div.poster > img").attr("src").toString()
		val poster = document.select("div.poster > img").attr("src").toString().replace("w185", "w500")
        val tags = document.select("div.sgeneros > a").map { it.text() }

        val year = Regex(",\\s?(\\d+)").find(
            document.select("span.date").text().trim()
        )?.groupValues?.get(1).toString().toIntOrNull()
        val tvTypeTag = if (document.select("ul#section > li:nth-child(1)").text().contains("Episodes")
        ) TvType.TvSeries else TvType.Movie
        val description = document.select("div.wp-content > p").text().trim()
        val trailer = document.selectFirst("div.embed iframe")?.attr("src")
        val rating =
            document.selectFirst("span.dt_rating_vgs")?.text()?.toRatingInt()
        val actors = document.select("div.persons > div[itemprop=actor]").map {
            Actor(it.select("meta[itemprop=name]").attr("content"), it.select("img").attr("src"))
        }

        val recommendation = document.select("div.sbox.srelacionados div.owl-item").mapNotNull {
    		val recName = it.selectFirst("img")?.attr("alt")?.replace(Regex("\\(\\d{4}\\)"), "")?.trim().toString() ?: return@mapNotNull null
			val recHref = it.selectFirst("a")?.attr("href") ?: return@mapNotNull null
    		val recPosterUrl = it.selectFirst("img")?.attr("src")
			println(recName)
			newMovieSearchResponse(
				recName, 
				recHref, 
				if (recHref.contains("/movie/")) TvType.Movie else TvType.TvSeries,
				false
			) {
        		this.posterUrl = recPosterUrl
    		}
		}


		recommendation.forEach { rec ->
    		println("Name: ${rec.name}, Url: ${rec.url}, Poster: ${rec.posterUrl}")
		}

		return if (tvTypeTag == TvType.TvSeries) {
			val description = document.select("div.content center p").joinToString("\n") { 
				it.text().trim() }
				.trim()
			val episodes = document.select("ul.episodios > li").map {
                val href = it.select("a").attr("href")
                val name = fixTitle(it.select("div.episodiotitle > a").text().trim())
                val image = it.select("div.imagen > img").attr("src")
                val episode = it.select("div.numerando").text().replace(" ", "").split("-").last()
                    .toIntOrNull()
                val season = it.select("div.numerando").text().replace(" ", "").split("-").first()
                    .toIntOrNull()
                newEpisode(href, {
                    this.name = name
                    this.episode = episode
                    this.season = season
                    this.posterUrl = image
                })
            }
            newTvSeriesLoadResponse(title, url, TvType.TvSeries, episodes) {
                this.posterUrl = poster
                this.year = year
                this.plot = description
                this.tags = tags
                this.rating = rating
                addActors(actors)
                this.recommendations = recommendation
                addTrailer(trailer)

				this.recommendations.forEach { rec ->
    				println("Name: ${rec.name}, Url: ${rec.url}, Poster: ${rec.posterUrl}")
				}

            }
        } else {
            newMovieLoadResponse(title, url, TvType.Movie, url) {
                this.posterUrl = poster
                this.year = year
                this.plot = description
                this.tags = tags
                this.rating = rating
                addActors(actors)
                this.recommendations = recommendation
                addTrailer(trailer)

				this.recommendations.forEach { rec ->
    				println("Name: ${rec.name}, Url: ${rec.url}, Poster: ${rec.posterUrl}")
				}
				
            }
        }
    }


override suspend fun loadLinks(
    data: String,
    isCasting: Boolean,
    subtitleCallback: (SubtitleFile) -> Unit,
    callback: (ExtractorLink) -> Unit
): Boolean {

    val document = app.get(data).document
    val elements = document.select("ul#playeroptionsul > li").map {
        Triple(
            it.attr("data-post"),
            it.attr("data-nume"),
            it.attr("data-type")
        )
    }

    // replaces apmap
    kotlinx.coroutines.coroutineScope {
        elements.map { (id, nume, type) ->
            async {
                val json = app.post(
                    url = "$directUrl/wp-admin/admin-ajax.php",
                    data = mapOf(
                        "action" to "doo_player_ajax",
                        "post" to id,
                        "nume" to nume,
                        "type" to type
                    ),
                    referer = data,
                    headers = mapOf("Accept" to "*/*", "X-Requested-With" to "XMLHttpRequest")
                ).parsedSafe<ResponseHash>() ?: return@async

                val metrix = AppUtils.parseJson<AesData>(json.embed_url).m
                val password = createKey(json.key, metrix)
                val decrypted = AesHelper.cryptoAESHandler(
                    json.embed_url,
                    password.toByteArray(),
                    false
                )?.fixBloat() ?: return@async

                when {
                    !decrypted.contains("youtube") ->
                        getUrl(decrypted, "$directUrl/", subtitleCallback, callback)
                    else -> return@async
                }
            }
        }.awaitAll() // waits for all async jobs
    }

    return true
}




/*    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {

        val document = app.get(data).document
        document.select("ul#playeroptionsul > li").map {
            Triple(
                it.attr("data-post"),
                it.attr("data-nume"),
                it.attr("data-type")
            )
        }.apmap { (id, nume, type) ->
            val json = app.post(
                url = "$directUrl/wp-admin/admin-ajax.php", data = mapOf(
                    "action" to "doo_player_ajax", "post" to id, "nume" to nume, "type" to type
                ), referer = data, headers = mapOf("Accept" to "*/
				/*
				*", "X-Requested-With" to "XMLHttpRequest")
            ).parsedSafe<ResponseHash>() ?: return@apmap
            val metrix = AppUtils.parseJson<AesData>(json.embed_url).m
            val password = createKey(json.key, metrix)
            val decrypted = AesHelper.cryptoAESHandler(json.embed_url, password.toByteArray(), false)?.fixBloat() ?: return@apmap
            
            when {
                !decrypted.contains("youtube") -> getUrl(decrypted, "$directUrl/", subtitleCallback, callback)
                else -> return@apmap
            }
        }

        return true
    }
*/

    private fun addBase64Padding(b64String: String): String {
        val padding = (4 - b64String.length % 4) % 4
        return b64String + "=".repeat(padding)
    }

    private fun createKey(r: String, e: String): String {
        // Split r into 2-char chunks, stepping by 4
        val rList = (2 until r.length step 4).map { r.substring(it, it + 2) }
        // Reverse e and pad
        val mPadded = addBase64Padding(e.reversed())

        val decodedM: String = try {
            String(Base64.getDecoder().decode(mPadded))
        } catch (ex: IllegalArgumentException) {
            println("Base64 decoding error: ${ex.message}")
            return ""
        }
        
        val decodedMList = decodedM.split("|")
        return decodedMList.mapNotNull { s ->
            s.toIntOrNull()?.takeIf { it < rList.size }?.let { "\\x${rList[it]}" }
        }.joinToString("")
    }

    
    private fun String.fixBloat(): String {
        return this.replace("\"", "").replace("\\", "")
    }

    private suspend fun getUrl(
        url: String,
        referer: String?,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val document = app.get(url, referer = referer).document
        val hash = url.split("/").last().substringAfter("data=")

        val m3usrc = app.post(
            url = "https://jeniusplay.com/player/index.php?data=$hash&do=getVideo",
            data = mapOf("hash" to hash, "r" to "$referer"),
            referer = referer,
            headers = mapOf("X-Requested-With" to "XMLHttpRequest")
        ).parsed<ResponseSource>().videoSource

        // Replace .txt at the end with .m3u8
        val m3uLink = m3usrc?.let {
            if (it.endsWith(".txt")) {
                it.substringBeforeLast(".") + ".m3u8"
            } else {
                it
            }
        }
        
        // println(m3uLink)

        if (!m3uLink.isNullOrBlank()) {
            M3u8Helper.generateM3u8(
                this.name,
                m3uLink,
                "$referer",
            ).forEach(callback)
        }


        document.select("script").map { script ->
            if (script.data().contains("eval(function(p,a,c,k,e,d)")) {
                val subData =
                    getAndUnpack(script.data()).substringAfter("\"tracks\":[").substringBefore("],")
                AppUtils.tryParseJson<List<Tracks>>("[$subData]")?.map { subtitle ->
                    subtitleCallback.invoke(
                        SubtitleFile(
                            getLanguage(subtitle.label ?: ""),
                            subtitle.file
                        )
                    )
                }
            }
        }
    }

    private fun getLanguage(str: String): String {
        return when {
            str.contains("indonesia", true) || str
                .contains("bahasa", true) -> "Indonesian"
            else -> str
        }
    }

    data class ResponseSource(
        @JsonProperty("hls") val hls: Boolean,
        @JsonProperty("videoSource") val videoSource: String,
        @JsonProperty("securedLink") val securedLink: String?,
    )

    data class Tracks(
        @JsonProperty("kind") val kind: String?,
        @JsonProperty("file") val file: String,
        @JsonProperty("label") val label: String?,
    )

    data class ResponseHash(
        @JsonProperty("embed_url") val embed_url: String,
        @JsonProperty("key") val key: String,
    )

    data class AesData(
        @JsonProperty("m") val m: String,
    )


}

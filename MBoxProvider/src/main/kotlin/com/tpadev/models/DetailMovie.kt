package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DetailMovie(
	@field:JsonProperty("referer")
	val referer: String? = null,

	@field:JsonProperty("isForbid")
	val isForbid: Boolean? = null,

	@field:JsonProperty("metadata")
	val metadata: Metadata? = null,

	@field:JsonProperty("resource")
	val resource: Resource? = null,

	@field:JsonProperty("subject")
	val subject: Subject? = null,

	@field:JsonProperty("forYou")
	val forYou: List<ForYouItem?>? = null,

	@field:JsonProperty("stars")
	val stars: List<StarsItem?>? = null,

	@field:JsonProperty("hot")
	val hot: Hot? = null,

	@field:JsonProperty("pubParam")
	val pubParam: MoviePubParam? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieCover(

	@field:JsonProperty("avgHueLight")
	val avgHueLight: String? = null,

	@field:JsonProperty("thumbnail")
	val thumbnail: String? = null,

	@field:JsonProperty("avgHueDark")
	val avgHueDark: String? = null,

	@field:JsonProperty("size")
	val size: Int? = null,

	@field:JsonProperty("gif")
	val gif: Any? = null,

	@field:JsonProperty("blurHash")
	val blurHash: String? = null,

	@field:JsonProperty("format")
	val format: String? = null,

	@field:JsonProperty("width")
	val width: Int? = null,

	@field:JsonProperty("url")
	val url: String? = null,

	@field:JsonProperty("height")
	val height: Int? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Metadata(

	@field:JsonProperty("image")
	val image: String? = null,

	@field:JsonProperty("keyWords")
	val keyWords: String? = null,

	@field:JsonProperty("referer")
	val referer: String? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForYouItem(

	@field:JsonProperty("subtitles")
	val subtitles: String? = null,

	@field:JsonProperty("keywords")
	val keywords: String? = null,

	@field:JsonProperty("releaseDate")
	val releaseDate: String? = null,

	@field:JsonProperty("channel")
	val channel: String? = null,

	@field:JsonProperty("hasResource")
	val hasResource: Boolean? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("staffList")
	val staffList: List<Any?>? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null,

	@field:JsonProperty("subjectId")
	val subjectId: String? = null,

	@field:JsonProperty("detailPath")
	val detailPath: String? = null,

	@field:JsonProperty("cover")
	val cover: MovieCover? = null,

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("trailer")
	val trailer: Any? = null,

	@field:JsonProperty("ops")
	val ops: String? = null,

	@field:JsonProperty("imgLoad")
	val imgLoad: Boolean? = null,

	@field:JsonProperty("genre")
	val genre: String? = null,

	@field:JsonProperty("imdbRatingValue")
	val imdbRatingValue: String? = null,

	@field:JsonProperty("countryName")
	val countryName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Subject(

	@field:JsonProperty("subtitles")
	val subtitles: String? = null,

	@field:JsonProperty("keywords")
	val keywords: String? = null,

	@field:JsonProperty("releaseDate")
	val releaseDate: String? = null,

	@field:JsonProperty("channel")
	val channel: String? = null,

	@field:JsonProperty("hasResource")
	val hasResource: Boolean? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("staffList")
	val staffList: List<Any?>? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null,

	@field:JsonProperty("subjectId")
	val subjectId: String? = null,

	@field:JsonProperty("detailPath")
	val detailPath: String? = null,

	@field:JsonProperty("cover")
	val cover: MovieCover? = null,

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("trailer")
	val trailer: Any? = null,

	@field:JsonProperty("ops")
	val ops: String? = null,

	@field:JsonProperty("genre")
	val genre: String? = null,

	@field:JsonProperty("imdbRatingValue")
	val imdbRatingValue: String? = null,

	@field:JsonProperty("countryName")
	val countryName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieItem(

	@field:JsonProperty("subtitles")
	val subtitles: String? = null,

	@field:JsonProperty("keywords")
	val keywords: String? = null,

	@field:JsonProperty("releaseDate")
	val releaseDate: String? = null,

	@field:JsonProperty("channel")
	val channel: String? = null,

	@field:JsonProperty("hasResource")
	val hasResource: Boolean? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("staffList")
	val staffList: List<Any?>? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null,

	@field:JsonProperty("subjectId")
	val subjectId: String? = null,

	@field:JsonProperty("detailPath")
	val detailPath: String? = null,

	@field:JsonProperty("cover")
	val cover: MovieCover? = null,

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("trailer")
	val trailer: Any? = null,

	@field:JsonProperty("ops")
	val ops: String? = null,

	@field:JsonProperty("genre")
	val genre: String? = null,

	@field:JsonProperty("imdbRatingValue")
	val imdbRatingValue: String? = null,

	@field:JsonProperty("countryName")
	val countryName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Resource(

	@field:JsonProperty("seasons")
	val seasons: List<SeasonsItem?>? = null,

	@field:JsonProperty("source")
	val source: String? = null,

	@field:JsonProperty("uploadBy")
	val uploadBy: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MoviePubParam(
	@field:JsonProperty("uid")
	val uid: String? = null,

	@field:JsonProperty("url")
	val url: String? = null,

	@field:JsonProperty("lang")
	val lang: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SeasonsItem(

	@field:JsonProperty("maxEp")
	val maxEp: Int? = null,

	@field:JsonProperty("se")
	val se: Int? = null,

	@field:JsonProperty("allEp")
	val allEp: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class StarsItem(

	@field:JsonProperty("character")
	val character: String? = null,

	@field:JsonProperty("avatarUrl")
	val avatarUrl: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("staffType")
	val staffType: Int? = null,

	@field:JsonProperty("staffId")
	val staffId: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TvItem(

	@field:JsonProperty("subtitles")
	val subtitles: String? = null,

	@field:JsonProperty("keywords")
	val keywords: String? = null,

	@field:JsonProperty("releaseDate")
	val releaseDate: String? = null,

	@field:JsonProperty("channel")
	val channel: String? = null,

	@field:JsonProperty("hasResource")
	val hasResource: Boolean? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("staffList")
	val staffList: List<Any?>? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null,

	@field:JsonProperty("subjectId")
	val subjectId: String? = null,

	@field:JsonProperty("detailPath")
	val detailPath: String? = null,

	@field:JsonProperty("cover")
	val cover: MovieCover? = null,

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("trailer")
	val trailer: Any? = null,

	@field:JsonProperty("ops")
	val ops: String? = null,

	@field:JsonProperty("genre")
	val genre: String? = null,

	@field:JsonProperty("imdbRatingValue")
	val imdbRatingValue: String? = null,

	@field:JsonProperty("countryName")
	val countryName: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Hot(

	@field:JsonProperty("tv")
	val tv: List<TvItem?>? = null,

	@field:JsonProperty("movie")
	val movie: List<MovieItem?>? = null
)

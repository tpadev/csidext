package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonProperty

data class HomeData(
	@field:JsonProperty("referer")
	val referer: String? = null,

	@field:JsonProperty("banner")
	val banner: Banner? = null,

	@field:JsonProperty("topPickList")
	val topPickList: List<Any?>? = null,

	@field:JsonProperty("homeList")
	val homeList: List<HomeListItem?>? = null,

	@field:JsonProperty("pubParam")
	val pubParam: PubParam? = null,

	@field:JsonProperty("allPlatform")
	val allPlatform: List<String?>? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

data class SubjectListItem(

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
	val cover: Cover? = null,

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

data class HomeListItem(

	@field:JsonProperty("genreId")
	val genreId: String? = null,

	@field:JsonProperty("rankingSubjectList")
	val rankingSubjectList: List<Any?>? = null,

	@field:JsonProperty("subjectList")
	val subjectList: List<SubjectListItem?>? = null,

	@field:JsonProperty("rankingGenreId")
	val rankingGenreId: String? = null,

	@field:JsonProperty("rankingTitle")
	val rankingTitle: String? = null,

	@field:JsonProperty("title")
	val title: String? = null
)


data class Image(

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

data class Banner(

	@field:JsonProperty("items")
	val items: List<ItemsItem?>? = null
)

data class RankingSubjectListItem(

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
	val cover: Cover? = null,

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

data class PubParam(

	@field:JsonProperty("uid")
	val uid: String? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

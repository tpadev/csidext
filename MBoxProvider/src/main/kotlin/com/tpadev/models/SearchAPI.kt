package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchAPI(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: Data? = null,

	@field:JsonProperty("message")
	val message: String? = null
)

data class Data(

	@field:JsonProperty("referer")
	val referer: String? = null,

	@field:JsonProperty("pager")
	val pager: Pager? = null,

	@field:JsonProperty("counts")
	val counts: List<CountsItem?>? = null,

	@field:JsonProperty("items")
	val items: List<SearchItemsItem?>? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

data class SearchItemsItem(

	@field:JsonProperty("subtitles")
	val subtitles: String? = null,

	@field:JsonProperty("keywords")
	val keywords: String? = null,

	@field:JsonProperty("releaseDate")
	val releaseDate: String? = null,

	@field:JsonProperty("hasResource")
	val hasResource: Boolean? = null,

	@field:JsonProperty("channel")
	val channel: String? = null,

	@field:JsonProperty("description")
	val description: String? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("staffList")
	val staffList: List<Any?>? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null,

	@field:JsonProperty("subjectId")
	val subjectId: String? = null,

	@field:JsonProperty("detailPath")
	val detailPath: String? = null,

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("cover")
	val cover: SearchCover? = null,

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

data class CountsItem(

	@field:JsonProperty("num")
	val num: Int? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("subjectType")
	val subjectType: Int? = null
)

data class SearchCover(

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

	@field:JsonProperty("width")
	val width: Int? = null,

	@field:JsonProperty("format")
	val format: String? = null,

	@field:JsonProperty("url")
	val url: String? = null,

	@field:JsonProperty("height")
	val height: Int? = null
)

data class Pager(

	@field:JsonProperty("perPage")
	val perPage: Int? = null,

	@field:JsonProperty("nextPage")
	val nextPage: String? = null,

	@field:JsonProperty("hasMore")
	val hasMore: Boolean? = null,

	@field:JsonProperty("page")
	val page: String? = null,

	@field:JsonProperty("totalCount")
	val totalCount: Int? = null
)

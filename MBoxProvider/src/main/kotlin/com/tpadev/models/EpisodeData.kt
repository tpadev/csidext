package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonProperty

data class EpisodeData(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: EpData? = null,

	@field:JsonProperty("message")
	val message: String? = null
)

data class StreamsItem(

	@field:JsonProperty("duration")
	val duration: Int? = null,

	@field:JsonProperty("size")
	val size: String? = null,

	@field:JsonProperty("format")
	val format: String? = null,

	@field:JsonProperty("resolutions")
	val resolutions: String? = null,

	@field:JsonProperty("id")
	val id: String? = null,

	@field:JsonProperty("codecName")
	val codecName: String? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

data class EpData(

	@field:JsonProperty("limitedCode")
	val limitedCode: String? = null,

	@field:JsonProperty("limited")
	val limited: Boolean? = null,

	@field:JsonProperty("streams")
	val streams: List<StreamsItem?>? = null,

	@field:JsonProperty("freeNum")
	val freeNum: Int? = null,

	@field:JsonProperty("dash")
	val dash: List<Any?>? = null,

	@field:JsonProperty("hls")
	val hls: List<Any?>? = null
)

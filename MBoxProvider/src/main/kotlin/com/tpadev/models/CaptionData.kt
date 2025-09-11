package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonProperty

data class CaptionData(

	@field:JsonProperty("code")
	val code: Int? = null,

	@field:JsonProperty("data")
	val data: CapData? = null,

	@field:JsonProperty("message")
	val message: String? = null
)

data class CaptionsItem(

	@field:JsonProperty("delay")
	val delay: Int? = null,

	@field:JsonProperty("size")
	val size: String? = null,

	@field:JsonProperty("lan")
	val lan: String? = null,

	@field:JsonProperty("id")
	val id: String? = null,

	@field:JsonProperty("lanName")
	val lanName: String? = null,

	@field:JsonProperty("url")
	val url: String? = null
)

data class CapData(
	@field:JsonProperty("captions")
	val captions: List<CaptionsItem?>? = null
)

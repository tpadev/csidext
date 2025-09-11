package com.tpadev.models

import com.fasterxml.jackson.annotation.JsonProperty


data class ItemsItem(

    @field:JsonProperty("image")
    val image: Image? = null,

    @field:JsonProperty("id")
    val id: String? = null,

    @field:JsonProperty("title")
    val title: String? = null,

    @field:JsonProperty("subjectType")
    val subjectType: Int? = null,

    @field:JsonProperty("subjectId")
    val subjectId: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
)

data class Cover(

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
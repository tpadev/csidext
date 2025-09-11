package com.tpadev.utils

import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.getQualityFromName

suspend fun loadAnimeSail(
    url: String,
    name: String,
    referer: String? = null,
    subtitleCallback: (SubtitleFile) -> Unit,
    callback: (ExtractorLink) -> Unit
): Boolean {
    val document = app.get(url).document
    val src = document.selectFirst("video source")?.attr("src") ?: return false
    callback(
        ExtractorLink(
            name,
            name,
            src,
            referer ?: url,
            getQualityFromName(name),
            ExtractorLinkType.M3U8,
        )
    )
    return true
}
package com.tpadev.utils

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import java.net.URI
import java.net.URL

@CloudstreamPlugin
class Dummy: Plugin() {
    override fun load(context: Context) {
    }
}

fun String.getLastNumber(): Int? {
    val regex = "\\d+".toRegex()
    val matches = regex.findAll(this)
    return matches.lastOrNull()?.value?.toInt()
}

fun String.isGofile(): Boolean {
    return try {
        val host = URL(this).host
        host.equals("gofile.io", ignoreCase = true)
    } catch (e: Exception) {
        false // Return false if the URL is malformed
    }
}

fun String.isMp4upload(): Boolean {
    return contains("mp4upload.com", ignoreCase = true)
}

fun String.toEmbedUrl(): String {
    val parts = this.split("/")
    if (isMp4upload()) {
        val videoId = parts.last()
        return "https://www.mp4upload.com/embed-$videoId.html"
    }
    return this
}

fun String.isUrlAbsolute(): Boolean {
    try {
        val uri = URI(this)
        return uri.isAbsolute
    } catch (e: Exception) {
        // If the URL is not well-formed, we'll assume it's relative
        return false
    }
}

fun String.toAbsoluteURL(domain: String): String {
    return if (isUrlAbsolute()) {
        this
    } else {
        "$domain$this"
    }
}

fun String.splitTitles(): Pair<String, String>? {
    val regex = Regex("(.+)(?=,\\s[\\p{InHiragana}\\p{InKatakana}\\p{InCJKUnifiedIdeographs}].+)")
    val matchResult = regex.find(this)
    return if (matchResult != null) {
        val firstTitle = matchResult.groupValues[1].trim()
        val secondTitle = substring(firstTitle.length + 1).trim()
        Pair(firstTitle, secondTitle)
    } else {
        Pair(this, this)
    }
}

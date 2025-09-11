package com.tpadev

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class PencurimovieProvider : Plugin() {
    override fun load(context: Context) {
        registerMainAPI(Pencurimovie())
    }
}

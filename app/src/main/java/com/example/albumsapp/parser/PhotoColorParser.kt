package com.example.albumsapp.parser

import android.graphics.Color
import java.util.*

class PhotoColorParser(private val url: String) {
    fun parse(): Int {
        var color = "#" + url.takeLastWhile { it != '/' }.uppercase(Locale.ROOT)
        while (color.length < 7) {
            color += "0"
        }
        return Color.parseColor(color)
    }
}
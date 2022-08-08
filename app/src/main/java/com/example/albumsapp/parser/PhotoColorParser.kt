package com.example.albumsapp.parser

import android.graphics.Color
import java.util.Locale

class PhotoColorParser {
    fun parse(url: String): Int {
        val color = StringBuilder("#")
        color.append(url.takeLastWhile { it != '/' }.uppercase(Locale.ROOT))
        while (color.length < 7) {
            color.insert(5, "0")
        }

        return Color.parseColor(color.toString())
    }
}

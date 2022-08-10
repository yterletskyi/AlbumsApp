package com.example.albumsapp.parser

import android.graphics.Color
import java.util.Locale

open class PhotoColorParser {
    fun parse(url: String): Int {
        val color = StringBuilder("#")
        color.append(url.takeLastWhile { it != '/' }.uppercase(Locale.ROOT))

        when (color.length) {
            6 -> color.insert(5, "0")
            4 -> {
                var index = 1
                while (index != color.length) {
                    color.insert(index + 1, color[index])
                    index++
                }
            }
            else -> {
                while (color.length < 7) {
                    color.append("0")
                }
            }
        }
        return Color.parseColor(color.toString())
    }
}

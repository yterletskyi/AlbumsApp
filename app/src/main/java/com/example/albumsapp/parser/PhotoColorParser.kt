package com.example.albumsapp.parser

import androidx.annotation.ColorInt

open class PhotoColorParser {
    @ColorInt
    fun parse(url: String): Int {
        val lastSegment = url.takeLastWhile { it != '/' }
        val colorIntRGB = lastSegment.toLong(16)

        val red = (colorIntRGB shr 16) and 0xff
        val green = (colorIntRGB shr 16) and 0xff
        val blue = colorIntRGB and 0xff

        return (0xff000000 or (red shl 16) or (green shl 8) or blue).toInt()
    }
}

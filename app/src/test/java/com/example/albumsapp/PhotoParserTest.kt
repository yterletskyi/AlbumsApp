package com.example.albumsapp

import android.graphics.Color
import com.example.albumsapp.parser.PhotoColorParser
import org.junit.Assert
import org.junit.Test

class PhotoParserTest {
    private val colorParser = PhotoColorParser()

    @Test
    fun testBlue() {
        val blueUrl = "https://placeholder.com/600/ff"
        val actual = colorParser.parse(blueUrl)
        Assert.assertEquals(Color.BLUE, actual)
    }

    @Test
    fun testBlack() {
        val blackUrl = "https://placeholder.com/600/0"
        val actual = colorParser.parse(blackUrl)
        Assert.assertEquals(Color.BLACK, actual)
    }

    @Test
    fun testWhite() {
        val whiteUrl = "https://placeholder.com/600/ffffff"
        val actual = colorParser.parse(whiteUrl)
        Assert.assertEquals(Color.WHITE, actual)
    }
}

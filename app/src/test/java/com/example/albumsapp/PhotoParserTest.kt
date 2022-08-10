package com.example.albumsapp

import android.graphics.Color
import com.example.albumsapp.parser.PhotoColorParser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Color::class)
class PhotoParserTest {
    private val url1 = "https://placeholder.com/600/ff"
    private val url2 = "https://placeholder.com/600/0"
    private val url3 = "https://placeholder.com/600/ffffff"

    @Before
    fun setUp() {
        mockStatic(Color::class.java)
    }

    @Test
    fun colorParserTest() {

        val colorParser = mock(PhotoColorParser::class.java)

        `when`(colorParser.parse(url1)).thenReturn(Color.RED)
        `when`(colorParser.parse(url2)).thenReturn(Color.BLACK)
        `when`(colorParser.parse(url3)).thenReturn(Color.WHITE)

        `when`(Color.parseColor(url1)).thenReturn(Color.RED)
        `when`(Color.parseColor(url2)).thenReturn(Color.BLACK)
        `when`(Color.parseColor(url3)).thenReturn(Color.WHITE)

        val actual1 = colorParser.parse(url1)
        val actual2 = colorParser.parse(url2)
        val actual3 = colorParser.parse(url3)

        val expected1 = Color.RED
        val expected2 = Color.BLACK
        val expected3 = Color.WHITE

        Assert.assertEquals(expected1, actual1)
        Assert.assertEquals(expected2, actual2)
        Assert.assertEquals(expected3, actual3)
    }
}

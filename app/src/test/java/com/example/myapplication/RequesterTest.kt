package com.example.myapplication

import Requester
import org.json.JSONObject
import org.json.JSONTokener
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RequesterTest {
    val requester = Requester()
    var foldername = "testFolder"

    @Before
    fun setUp() {
        //add your api key from your syncthing instance
        requester.apiKey = ""
    }


    @Test
    fun getHealth() {
        assertEquals(200, requester.getHealth().code())
    }


    @Test
    fun getDefaultFolder() {
        var jsonObject =
            JSONTokener(requester.getDefaultFolder().body()?.string()).nextValue() as JSONObject
        assertEquals("", jsonObject.get("id"))

    }

    @Test
    fun overgiveFolder() {
        var path = "~/test"
        requester.overgiveFolder(foldername, path)
        assertEquals(200, requester.getFolder(foldername).code())

    }

    @Test
    fun deleteFolder() {
        requester.deleteFolder(foldername)
        assertEquals(404, requester.getFolder(foldername).code())
    }
}
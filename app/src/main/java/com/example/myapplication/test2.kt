package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class GetExample {
    val client: OkHttpClient = OkHttpClient()
    @Throws(IOException::class)
    fun run(url: String?): String {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body()?.string() ?:"test"  }
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val example = GetExample()
            val response = example.run("http://127.0.0.1:8384")
            println(response)
        }
    }
}
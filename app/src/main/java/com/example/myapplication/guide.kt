package okhttp3.guide

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class GetExample {
    val client = OkHttpClient()
    @Throws(IOException::class)
    fun run(url: String?): String {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body()!!.string() }
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val example = GetExample()
            val response = example.run("https://raw.github.com/square/okhttp/master/README.md")
            println(response)
        }
    }
}
import okhttp3.*
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL


fun main() {

    val path = "~/test"
    val requester = Requester()
    print(requester.getFolder("testFolder").body()?.string())
}

class Requester {
    var apiKey = ""
    val address = "http://127.0.0.1"
    val port = "8384"
    val client = OkHttpClient()
    val url = address + ":" + port

    fun getConnections(): Response {

        val request: Request = Request.Builder()
            .url(URL("$url/rest/system/connections"))
            .header("X-API-Key", apiKey)
            .get()
            .build()

        return client.newCall(request).execute()

    }

    fun getHealth(): Response {

        val request: Request = Request.Builder()
            .url(URL("$url/rest/noauth/health"))
            .get()
            .build()

        return client.newCall(request).execute()
    }


    fun getVersion(): Response {

        val request: Request = Request.Builder()
            .url(URL("$url/rest/noauth/version"))
            .header("X-API-Key", apiKey)
            .get()
            .build()

        return client.newCall(request).execute()
    }

    fun getDefaultFolder(): Response {

        val request: Request = Request.Builder()
            .url(URL("$url/rest/config/defaults/folder"))
            .header("X-API-Key", apiKey)
            .get()
            .build()

        return client.newCall(request).execute()
    }

    fun overgiveFolder(foldername: String, path: String): Response {
        val response = getDefaultFolder().body()?.string()
        val jsonObject = JSONTokener(response).nextValue() as JSONObject
        jsonObject.put("id", foldername)
        jsonObject.put("label", "Signal Backup")
        jsonObject.put("path", path)
        jsonObject.put("type", "sendonly")

        val JSON = MediaType.parse("application/json")
        val body = RequestBody.create(JSON, jsonObject.toString())

        val request: Request = Request.Builder()
            .url(URL("$url/rest/config/folders"))
            .header("X-API-Key", apiKey)
            .post(body)
            .build()

        return client.newCall(request).execute()


    }

    fun deleteFolder(folderid: String): Response {
        val request: Request = Request.Builder()
            .url(URL("$url/rest/config/folders/$folderid"))
            .header("X-API-Key", apiKey)
            .delete()
            .build()

        return client.newCall(request).execute()
    }

    fun getFolder(folderid: String): Response {
        val request: Request = Request.Builder()
            .url(URL("$url/rest/config/folders/$folderid"))
            .header("X-API-Key", apiKey)
            .get()
            .build()

        return client.newCall(request).execute()
    }


    /**Override a send only folder, makes the local version latest ->
     * overrides changes made on connected device for this folder*/
    fun overrideFolder(folderid: String): Response {
        val request: Request = Request.Builder()
            .url(URL("$url/rest/db/override?folder=$folderid"))
            .header("X-API-Key", apiKey)
            .post(null)
            .build()

        return client.newCall(request).execute()

    }
}


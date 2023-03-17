

import okhttp3.*
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL


val apiKey = "5nJQng3zSrLNgKvXGVgww6YNSEHvQpFQ"
val apikey2 = "YAtSebDvde2cg9dj36MbVUuVidZ5q9oF"
val adress = "http://127.0.0.1"
val port = "8384"
val client = OkHttpClient()


val url = adress + ":" + port

// Request a string response from the provided URL.





fun main() {

    val path = "C:/Users/Fabian Richters/test"

    print(overgiveFolder("name3", path).body()?.string())
}




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

fun overgiveFolder(foldername: String, path: String): Response{
    val response = getDefaultFolder().body()?.string()
    val jsonObject = JSONTokener(response).nextValue() as JSONObject




    jsonObject.put("id", foldername + "1")
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

fun deleteFolder(folderid: String): Response{
    val request: Request = Request.Builder()
        .url(URL("$url//rest/config/folders/$folderid"))
        .header("X-API-Key", apiKey)
        .get()
        .build()

    return client.newCall(request).execute()
}

//fun deleteDevice(deviceid: String): HttpRequest{
//    val request = HttpRequest.newBuilder()
//        .uri(URI(url + "/rest/config/devices/" + deviceid))
//        .headers("X-API-Key", apiKey)
//        .GET()
//        .build();
//    return request
//}
//
//fun getFolder(folderid: String): HttpRequest{
//    val request = HttpRequest.newBuilder()
//        .uri(URI(url + "/rest/config/folders/" + folderid))
//        .headers("X-API-Key", apiKey)
//        .GET()
//        .build();
//    val test = 0
//    return request
//}
//
///**Override a send only folder, makes the local version latest ->
// * overrides changes made on connected device for this folder*/
//fun overrideFolder(folderid: String): HttpRequest{
//    val request = HttpRequest.newBuilder()
//        .uri(URI(url + "/rest/db/override?folder=" + folderid))
//        .header("X-API-Key", apiKey)
//        .POST(HttpRequest.BodyPublishers.noBody())
//        .build();
//    return request
//}
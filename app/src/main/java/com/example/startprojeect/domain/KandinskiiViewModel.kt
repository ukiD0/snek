package com.example.startprojeect.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class KandinskiiViewModel:ViewModel() {
    var modelID = ""
    var uuid = ""
    var status = ""
    var bitmap: Bitmap? = null

//    fun getIdModel(){
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://api-key.fusionbrain.ai/key/api/v1/models")
//            .addHeader("X-Key", "Key F4E0CCE26FEC0B5EDA1AA2C661FD58F7")
//            .addHeader("X-Secret", "Secret BE0FC3C61F3E6B01CC7371FD37D6DA68")
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val jsonArray = JSONArray(response.body)
//                modelID = jsonArray.getJSONObject(0).getInt("id").toString()
//                Log.e("huinya","huinya")
//            }
//
//        })
//    }

    fun generateImage(promt:String){
        val client = OkHttpClient()
        val body = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("params", null,
                "{\n  \"type\": \"GENERATE\",\n  \"generateParams\": {\n    \"query\": \"$promt\"\n  }\n}".toRequestBody("application/json".toMediaType()))
            .addFormDataPart("model_id","4")
            .build()
        val request = Request.Builder()
            .url("https://api-key.fusionbrain.ai/key/api/v1/text2image/run")
            .post(body)
            .addHeader("X-Key", "Key F4E0CCE26FEC0B5EDA1AA2C661FD58F7")
            .addHeader("X-Secret", "Secret BE0FC3C61F3E6B01CC7371FD37D6DA68")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
               Log.e("failuer","faiiilll")
            }
            override fun onResponse(call: Call, response: Response) {
//                Log.e("respon1",response.body?.string().toString())
                val jsonArray = JSONObject(response.body?.string())
                uuid = jsonArray.getString("uuid")

            }
        })
    }
    suspend fun getImage() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api-key.fusionbrain.ai/key/api/v1/text2image/status/${uuid}")
            .addHeader("X-Key", "Key F4E0CCE26FEC0B5EDA1AA2C661FD58F7")
            .addHeader("X-Secret", "Secret BE0FC3C61F3E6B01CC7371FD37D6DA68")
            .build()
        if (status != "DONE") {
             client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("failureGEtImage","hello")
                }

                override fun onResponse(call: Call, response: Response) {
                    val jsonArray = JSONObject(response.body?.string())
                    status = jsonArray.getString("status")
                    if (status == "DONE") {
                        var b64Image = jsonArray.getJSONArray("images").getString(0)
                        var image = Base64.decode(b64Image, Base64.DEFAULT)
                        bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
                    }
                    Log.e("huinya", "huinya")
                }
            })
                delay(3000)
                getImage()
        }
    }

}
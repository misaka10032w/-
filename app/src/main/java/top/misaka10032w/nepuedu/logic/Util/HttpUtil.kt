package top.misaka10032w.nepuedu.logic.Util

import okhttp3.OkHttpClient
import okhttp3.Request
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.logic.network.HttpCallbackListener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {
    fun sendHttpRequest(address:String,listener: HttpCallbackListener){
        thread {
        var connection:HttpURLConnection?=null
        try {
            val response=StringBuilder()
            val url= URL(address)
            connection=url.openConnection() as HttpURLConnection
            connection.connectTimeout=8000
            connection.readTimeout=8000
            val input=connection.inputStream
            val reader=BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    response.append(it)
                }
            }
          listener.onFinish(response.toString())
        }catch (e:Exception){
            e.printStackTrace()
            listener.onError(e)
        }finally {
            connection?.disconnect()
        }
    }
    }

    fun sendOkhttpRequest(address: String,callback:okhttp3.Callback){
        val client=OkHttpClient()
        val request=Request.Builder()
            .url(address)
            .addHeader("User-Agent",DyjwApplication.USERAGENT)
            .build()
        client
            .newCall(request)
            .enqueue(callback)
    }
}
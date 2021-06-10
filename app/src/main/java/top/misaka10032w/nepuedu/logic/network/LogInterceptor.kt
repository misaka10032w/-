package top.misaka10032w.nepuedu.logic.network

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*

class LogInterceptor:Interceptor {
    val tag="Retrofit"
    @SuppressLint("SimpleDateFormat")
    val format=SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        Log.v(tag,format.format(Date())+"Request"+"\nmethod:"+request.method+"\nurl:"+request.url+"\nbody:"+request.body)
        val response=chain.proceed(request)
        Log.v("Retrofit",response.peekBody(4096).string())
        return response
    }

}
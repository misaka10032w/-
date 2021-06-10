package top.misaka10032w.nepuedu.logic.network

import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ServiceCreator {
    const val BASEURL = "https://jwgl.webvpn.nepu.edu.cn/"
    private val client = OkHttpClient.Builder()
        .addInterceptor(LogInterceptor())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}
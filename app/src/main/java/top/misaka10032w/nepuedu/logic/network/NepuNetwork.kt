package top.misaka10032w.nepuedu.logic.network

import android.util.Log
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object NepuNetwork {
    private val loginVpnService=ServiceCreator.create(LoginVpnService::class.java)
    suspend fun loginVpn()= loginVpnService.loginVpn().await()
    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body =response.body()
                    Log.v("test",response.body().toString())
                    if (body!=null)continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
            }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}

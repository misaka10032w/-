package top.misaka10032w.nepuedu.logic.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import top.misaka10032w.nepuedu.logic.model.dd


interface LoginVpnService {
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36")
    @GET("users/sign_in")
    fun loginVpn():Call<ResponseBody>

}
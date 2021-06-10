package top.misaka10032w.nepuedu.logic

import android.util.Log
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import okhttp3.Call
import okhttp3.Callback
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.logic.Util.HttpUtil
import java.io.IOException

object Repository {
    fun loginVpn() = liveData(Dispatchers.IO) {
        val result = try {
            val address=DyjwApplication.VPNURL+"users/sign_in"
            HttpUtil.sendOkhttpRequest(address, object : Callback {
                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val responseData = response.body?.string()
                    Log.v("test",responseData.toString())
                }
                override fun onFailure(call:Call,e:IOException){

                }
            })
        } catch (e: Exception) {

        }
        emit(result)
        Log.v("test", result.toString())
    }

}
package top.misaka10032w.nepuedu

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class DyjwApplication : Application() {
    companion object {
        const val VPNURL = "https://webvpn.nepu.edu.cn/"
        const val BASEURL = "https://jwgl.webvpn.nepu.edu.cn/"
        const val USERAGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
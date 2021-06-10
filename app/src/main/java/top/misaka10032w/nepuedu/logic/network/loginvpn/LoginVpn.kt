package top.misaka10032w.nepuedu.logic.network.loginvpn

import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import top.misaka10032w.nepuedu.DyjwApplication
import kotlin.concurrent.thread

class LoginVpn {
   fun LoginVpn(stu_num: String, vpn_psw: String) {
runBlocking {
    async {
    thread {

        val getvpntoken: Connection.Response =
            Jsoup.connect(DyjwApplication.VPNURL + "users/sign_in")
                .userAgent(DyjwApplication.USERAGENT)
                .timeout(3000)
                .execute()

        var cookies: Map<String?, String?>? = getvpntoken.cookies()
        val doc = Jsoup.parse(getvpntoken.body())
        val TOKEN = doc.getElementsByAttributeValue("name", "csrf-token").attr("content")
        val list: ArrayList<Element>
        list = doc.select("form")
        val datas = HashMap<String, String>()
        for (list in list.get(0).allElements) {
            if (list.attr("name") == "user[login]") {
                list.attr("value", stu_num)
            }
            if (list.attr("name") == "user[password]") {
                list.attr("value", vpn_psw)
            }
            if (list.attr("name").isNotEmpty()) {
                datas.put(list.attr("name"), list.attr("value"))
            }
        }
        val loginvpn: Connection.Response =
            Jsoup.connect(DyjwApplication.VPNURL + "users/sign_in")
                .userAgent(DyjwApplication.USERAGENT)
                .timeout(3000)
                .ignoreContentType(true)
                .followRedirects(true)
                .cookies(cookies)
                .data(datas)
                .method(Connection.Method.POST)
                .execute()
        val cookiesforlogin: Map<String?, String?>
        cookiesforlogin = loginvpn.cookies()
        val doc2 = Jsoup.parse(loginvpn.body())
        val name = doc2.getElementsByClass("dropdown-toggle").text()
        val _webvpn_key = cookiesforlogin["_webvpn_key"]
        val webvpn_username = cookiesforlogin["webvpn_username"]
        if (name.length != 0) {
            val editor =
                DyjwApplication.context.getSharedPreferences(
                    "login_info",
                    AppCompatActivity.MODE_PRIVATE
                )
                    .edit()
            editor.putString("_webvpn_key", _webvpn_key)
            editor.putString("webvpn_username", webvpn_username)
            editor.putString("stu_realname", name)
            editor.putString("stu_num", stu_num)
            editor.putString("vpn_psw", vpn_psw)
            editor.putBoolean("login_flag", true)
            editor.apply()
        } else {
            Looper.prepare()
            Toast.makeText(DyjwApplication.context, "用户名或密码错误0", Toast.LENGTH_SHORT).show()
            Looper.loop()
        }

        Log.v("vpn", "------------------------------------------")
        Log.v("vpn", "用户名\n${name.length}")
        Log.v("vpn", "Cookies\n$cookies")
        Log.v("vpn", "请求头\n$datas")
        Log.v("vpn", "Token\n$TOKEN")
        Log.v("vpn", "cookiesforlogin\n$cookiesforlogin")
        Log.v("vpn", "------------------------------------------")
    }
}.await()
}
    }
}

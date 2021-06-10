package top.misaka10032w.nepuedu.logic.network

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import top.misaka10032w.nepuedu.DyjwApplication
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class LoginEdu {
     fun LoginEdu(
        stu_num:String,
        stu_psw:String,
            capchacode: String,
            sessionID: String,
            webvpn_username: String,
            _webvpn_key: String
        ) {
            val encryptkey = capchacode + capchacode + capchacode + capchacode
            val encryptedpassword = encrypt(stu_psw, encryptkey)
            Log.v("login_info", encryptkey)
            Log.v("login_info", encryptedpassword)
            Log.v("login_info", sessionID)
            Thread {
                val client2 = OkHttpClient()
                val requestBody = FormBody.Builder()
                    .add("account", stu_num)
                    .add("pwd", stu_psw)
                    .add("verifycode", capchacode)
                    .build()
                val request = Request.Builder()
                    .url(DyjwApplication.BASEURL + "new/login")
                    .header("User-Agent", DyjwApplication.USERAGENT)
                    .header(
                        "Cookie",
                        "webvpn_username=$webvpn_username;_webvpn_key=$_webvpn_key;$sessionID"
                    )
                    .post(requestBody)
                    .build()
                val response = client2
                    .newCall(request)
                    .execute()
                val responseData = response.body?.string()
                Log.v("login_info", responseData.toString())
            }.start()
        }

    }
    @SuppressLint("GetInstance")
    private fun encrypt(input: String, password: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val keySpec = SecretKeySpec(password.toByteArray(Charset.forName("UTF-8")), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val encrypted = cipher.doFinal(input.toByteArray())
        val stringBuilder = StringBuilder("")
        for (element in encrypted) {
            val v = element.toInt() and 0xFF
            val hv = Integer.toHexString(v)
            if (hv.length < 2) {
                stringBuilder.append(0)
            }
            stringBuilder.append(hv)
        }
        return stringBuilder.toString()
    }

package top.misaka10032w.nepuedu.fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.R
import top.misaka10032w.nepuedu.databinding.FragmentLoginEduThBinding
import top.misaka10032w.nepuedu.logic.network.LoginEdu
import kotlin.concurrent.thread

class LoginEduThFragment : Fragment() {
    private var _binding: FragmentLoginEduThBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginEduThBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pref = activity?.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val stu_num = pref?.getString("stu_num", "0")
        val webvpn_username = pref?.getString("webvpn_username", "0")
        val _webvpn_key = pref?.getString("_webvpn_key", "0")
        binding.stuNumInput.setText(stu_num)
        if (webvpn_username != null) {
            if (_webvpn_key != null) {
                getcapcha(webvpn_username, _webvpn_key)
            }
        }

        binding.loginBtn.setOnClickListener {
            val edu_psw = binding.eduPswInput.text
            if (edu_psw?.length!! > 5) {
                val stu_psw = binding.eduPswInput.text.toString()
                val capchacode = binding.capchaInput.text.toString()
                val sessionID = pref?.getString("sessionID", "0")
                println(stu_num)
                println(sessionID)
                println(webvpn_username)
                println(_webvpn_key)
                if (stu_num != null && sessionID != null && webvpn_username != null && _webvpn_key != null) {
                    LoginEdu().LoginEdu(
                        stu_num,
                        stu_psw,
                        capchacode,
                        sessionID,
                        webvpn_username,
                        _webvpn_key
                    )
                }



            }
            findNavController().navigate(R.id.edu_index_nav_item)
        }
        binding.capchaImg.setOnClickListener {
            if (webvpn_username != null && _webvpn_key != null) {

                getcapcha(webvpn_username, _webvpn_key)

            }
        }
    }

    private fun  getcapcha(webvpn_username: String, _webvpn_key: String) {
        thread {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(DyjwApplication.BASEURL + "yzm")
                .header("User-Agent", DyjwApplication.USERAGENT)
                .header("Cookie", "webvpn_username=$webvpn_username;_webvpn_key=$_webvpn_key")
                .build()
            val response = client
                .newCall(request)
                .execute()
            val responseData: ByteArray? = response.body?.bytes()


            val headers: Headers = response.headers
            val cookies: List<String> = headers.values("Set-Cookie")
            val session = cookies[0]
            val sessionID = session.substring(0, session.indexOf(";"))
            val pref = activity?.getSharedPreferences("login_info", AppCompatActivity.MODE_PRIVATE)
                ?.edit()
            val nowtime = System.currentTimeMillis() / 1000
            pref?.putString("sessionID", sessionID)
            pref?.putLong("session_time", nowtime)
                ?.apply()
            Log.i("info_s", "session is :" + sessionID)
            activity?.runOnUiThread {
                val bitmap =
                    responseData?.let {
                        BitmapFactory.decodeByteArray(
                            responseData,
                            0,
                            it.size
                        )
                    }

                binding.capchaImg.setImageBitmap(bitmap)
            }
        }
    }
}
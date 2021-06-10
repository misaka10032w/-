package top.misaka10032w.nepuedu.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.R
import top.misaka10032w.nepuedu.databinding.FragmentLoginEduBinding
import top.misaka10032w.nepuedu.logic.network.loginvpn.LoginVpn


class LoginEduFragment : Fragment() {
    private var _binding: FragmentLoginEduBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginEduBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pref = activity?.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val stu_num = pref?.getString("stu_num", "0")
        val vpn_psw = pref?.getString("vpn_psw", "0")
        val login_flag = pref?.getBoolean("login_flag", false)

        if (login_flag == true) {
            binding.stuNumInput.setText(stu_num)
            binding.vpnPswInput.setText(vpn_psw)
        }

        binding.loginBtn.setOnClickListener {
            val stunum = binding.stuNumInput.text.toString()
            val vpnpsw = binding.vpnPswInput.text.toString()

            if (stunum.length > 5 || vpnpsw.length > 5) {
                runBlocking {


                        binding.loginBtn.isEnabled = false
                        LoginVpn().LoginVpn(stunum, vpnpsw)

                    async {
                        val login_flag = pref?.getBoolean("login_flag", false)
                        Log.v("test", login_flag.toString())
                        if (login_flag == true) {

                            findNavController().navigate(R.id.loginEduTh_Fragment)
                        } else {
                            binding.loginBtn.isEnabled = true
                            Toast.makeText(DyjwApplication.context, "用户名或密码错误", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }.await()
                }


            } else {
                Toast.makeText(DyjwApplication.context, "请输入正确的用户名和密码", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
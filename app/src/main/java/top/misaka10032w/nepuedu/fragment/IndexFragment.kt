package top.misaka10032w.nepuedu.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import top.misaka10032w.nepuedu.DyjwApplication
import top.misaka10032w.nepuedu.R
import top.misaka10032w.nepuedu.databinding.FragmentIndexBinding

class IndexFragment : Fragment() {
    private var _binding: FragmentIndexBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentIndexBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("ResourceType")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pref = activity?.getSharedPreferences("login_flag", Context.MODE_PRIVATE)
        val login_flag = pref?.getBoolean("login_flag", false)
        if (login_flag == true) {

            Toast.makeText(DyjwApplication.context, "请登录", Toast.LENGTH_SHORT).show()
            Log.v("login", "未登录")
            findNavController().navigate(R.id.loginEduFragment)

        }
        binding.scoreBtn.setOnClickListener {
            findNavController().navigate(R.id.scoreFragment)
        }

        binding.resitBtn.setOnLongClickListener {
            Toast.makeText(DyjwApplication.context, "长按", Toast.LENGTH_SHORT).show()
            true
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("login", "被创建")
    }


}
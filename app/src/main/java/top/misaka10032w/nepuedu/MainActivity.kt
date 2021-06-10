package top.misaka10032w.nepuedu

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import top.misaka10032w.nepuedu.databinding.ActivityMainBinding
import top.misaka10032w.nepuedu.fragment.LoginEduFragment
import java.security.KeyFactory

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT

    }

    private fun initView() {
          val  navHostFragment:NavHostFragment= supportFragmentManager.findFragmentById(R.id.nav_host_fragment)as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }

    fun onCheckboxClicked(view:View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when(view.id) {
                R.id.same_pass -> {
                    if (checked){
                        Log.v("test","xuanzhong")
                    }else{
                        Log.v("test","weixuanzhong")
                    }
                }
            }
        }
    }
}
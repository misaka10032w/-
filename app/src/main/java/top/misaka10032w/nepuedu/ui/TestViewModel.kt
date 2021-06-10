package top.misaka10032w.nepuedu.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import top.misaka10032w.nepuedu.logic.Repository

class TestViewModel:ViewModel(){
    val respon= MutableLiveData<String>()
    val livedata= Transformations.switchMap(respon){
        Repository.loginVpn()

    }
}
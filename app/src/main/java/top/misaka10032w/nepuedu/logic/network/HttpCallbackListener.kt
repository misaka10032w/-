package top.misaka10032w.nepuedu.logic.network

interface HttpCallbackListener {
    fun onFinish(response:String)
    fun onError(e:Exception)
}
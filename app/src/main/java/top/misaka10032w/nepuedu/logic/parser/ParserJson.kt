package top.misaka10032w.nepuedu.logic.parser

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import top.misaka10032w.nepuedu.logic.model.ScoreJsonBean
import top.misaka10032w.nepuedu.logic.model.scoreitems

class ParserJson {
    private val scoreList = ArrayList<scoreitems>()
    fun parsescorejson(jsondata: String) {

        try {
            var scorecolor: Long
            val gson = Gson()
            val typeOf = object : TypeToken<ScoreJsonBean?>() {}.type
            val ArrayList = gson.fromJson<ScoreJsonBean>(jsondata, typeOf)
            println("results")
            for (Row in ArrayList.rows) {
                if (Row.zcj.toInt() < 60) {
                    scorecolor = 0XFF4CAF50
                } else {
                    scorecolor = 0XFFF44336
                }
                scoreList.add(
                    scoreitems(
                        Row.kcmc,
                        "开课时间" + Row.xnxqmc,
                        "学分" + Row.xf,
                        Row.zcj + " ",
                        scorecolor.toInt(),
                    )
                )
                Log.v(
                    "score",
                    Row.toString()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
package top.misaka10032w.nepuedu.logic

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.samlss.broccoli.Broccoli
import me.samlss.broccoli.BroccoliGradientDrawable
import me.samlss.broccoli.PlaceholderParameter
import top.misaka10032w.nepuedu.databinding.ScoreItemBinding
import top.misaka10032w.nepuedu.logic.Util.BroccoliManager
import top.misaka10032w.nepuedu.logic.model.scoreitems

class ScoreAdapter(val context: Context, val scoreList: ArrayList<scoreitems>) :
    RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ScoreItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val courseName: TextView = binding.courseName
        val openingTime: TextView = binding.openingTime
        val credit: TextView = binding.credit
        val score: TextView = binding.scoreItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ScoreItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

//        val broccoli = Broccoli()
//        broccoli.addPlaceholder(
//            PlaceholderParameter.Builder()
//                .setView(binding.courseName)
//                .setDrawable(
//                    BroccoliGradientDrawable(
//                        Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
//                        0F, 1000, LinearInterpolator()
//                    )
//                ).build()
//        ).addPlaceholder(
//            PlaceholderParameter.Builder()
//                .setView(binding.openingTime)
//                .setDrawable(
//                    BroccoliGradientDrawable(
//                        Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
//                        0F, 1000, LinearInterpolator()
//                    )
//                ).build()
//        ).addPlaceholder(
//            PlaceholderParameter.Builder()
//                .setView(binding.credit)
//                .setDrawable(
//                    BroccoliGradientDrawable(
//                        Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
//                        0F, 1000, LinearInterpolator()
//                    )
//                ).build()
//        )
//            .addPlaceholder(
//                PlaceholderParameter.Builder()
//                    .setView(binding.scoreItem)
//                    .setDrawable(
//                        BroccoliGradientDrawable(
//                            Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
//                            0F, 1000, LinearInterpolator()
//                        )
//                    ).build()
//            )
//        broccoli.show()
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        BroccoliManager.initRecyclerView(holder.itemView
            ,holder.courseName
            , holder.credit
            , holder.openingTime
            , holder.score)

        val scores = scoreList[position]
        Log.v("11111111111", scores.coursename)
        if (scores.coursename!="0") {

    holder.courseName.text = scores.coursename
    holder.credit.text = scores.credit
    holder.openingTime.text = scores.openingtime
    holder.score.text = scores.score
    holder.score.setTextColor(scores.scorecolor)

     BroccoliManager.byItemViewClear(holder.itemView)
}
}


    override fun getItemCount() = scoreList.size

}

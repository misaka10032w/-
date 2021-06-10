package top.misaka10032w.nepuedu.logic.Util

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.DrawableUtils
import me.samlss.broccoli.Broccoli
import me.samlss.broccoli.BroccoliGradientDrawable
import me.samlss.broccoli.PlaceholderParameter


object BroccoliManager {
    private var mBroccoli: Broccoli? = null

    // 给Recycler的item用的。
    private val mViewPlaceholderManager: MutableMap<View, Broccoli> = HashMap()

    /**
     * 静态的占位符
     *
     * @param context 上下文 activity
     * @param views   R.id.控件的id
     */
    fun initStatic(context: Activity?, vararg views: Int) {
        if (mBroccoli == null) {
            mBroccoli = Broccoli()
        }
        for (view in views) {
            mBroccoli!!.addPlaceholders(context, view)
        }
    }

    /**
     * 动态占位符
     *
     * @param views 已绑定好的view
     */
    fun initAction(vararg views: View?) {
        mBroccoli = Broccoli()
        for (view in views) {
            mBroccoli!!.addPlaceholders(view)
        }
    }

    /**
     * RecyclerView的动态占位符
     *
     * @param itemView recyclerView整项内容的View
     * @param views    里面各小项内容的view
     */
    fun initRecyclerView(itemView: View, vararg views: View?) {
        var broccoli = mViewPlaceholderManager[itemView]
        if (broccoli == null) {
            broccoli = Broccoli()
            mViewPlaceholderManager[itemView] = broccoli
        }
        for (view in views) {
            broccoli.addPlaceholder(
                PlaceholderParameter.Builder()
                    .setView(view)
                    .setDrawable(
                        BroccoliGradientDrawable(
                            Color.parseColor("#DDDDDD"),
                            Color.parseColor("#CCCCCC"), 0F, 1000, LinearInterpolator()
                        )
                    )
                    .build()
            )
        }
        broccoli.show()
    }

    /**
     * 根据大项itemView来清除占位符
     *
     * @param itemView
     */
    fun byItemViewClear(itemView: View?) {
        if (itemView != null) {
            val broccoli = mViewPlaceholderManager[itemView]
            broccoli!!.removeAllPlaceholders()
        }
    }

    /**
     * 当用recyclerView时在onDestroy时将mViewPlaceholderManager清空
     * 防止使用BroccoliGradientDrawable时内存泄露
     */
    fun recyclerViewOnDestroy() {
        //Prevent memory leaks when using BroccoliGradientDrawable
        //防止使用BroccoliGradientDrawable时内存泄露
        for (broccoli in mViewPlaceholderManager.values) {
            broccoli.removeAllPlaceholders()
        }
        mViewPlaceholderManager.clear()
    }

    /**
     * 清除静态占位符
     */
    fun staticClear() {
        if (mBroccoli != null) {
            mBroccoli!!.removeAllPlaceholders()
        }
    }

    /**
     * 圆形占位符
     *
     * @param views 可以多个圆形view 进行处理
     */
    fun staticSquare(vararg views: View?) {
        val placeholderColor: Int = Color.parseColor("#DDDDDD")
        if (mBroccoli == null) {
            mBroccoli = Broccoli()
        }
        for (view in views) {
            mBroccoli!!.addPlaceholders(
                PlaceholderParameter.Builder()
                    .setView(view)
                    .setDrawable(
                        BroccoliGradientDrawable(
                            Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
                            0F, 1000, LinearInterpolator()
                        )
                    ).build()
            )
        }
    }

    fun show() {
        mBroccoli!!.show()
    }

    /**
     * 圆形占位符
     *
     * @param views 可以多个圆形view 进行处理
     */
    fun staticCircular(vararg views: View?) {
        val placeholderColor: Int = Color.parseColor("#DDDDDD")
        if (mBroccoli == null) {
            mBroccoli = Broccoli()
        }
        for (view in views) {
            mBroccoli!!.addPlaceholders(
                PlaceholderParameter.Builder()
                    .setView(view)
                    .setDrawable(
                        BroccoliGradientDrawable(
                            Color.parseColor("#DDDDDD"), Color.parseColor("#cccccc"),
                            0F, 1000, LinearInterpolator()
                        )
                    ).build()
            )
        }
    }
}
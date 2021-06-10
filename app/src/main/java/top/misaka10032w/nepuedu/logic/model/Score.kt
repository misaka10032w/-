package top.misaka10032w.nepuedu.logic.model

import com.google.gson.annotations.SerializedName

data class scoreitems(
    val coursename: String,
    val openingtime:String,
    val credit:String,
    val score:String,
    val scorecolor:Int
)

class ScoreJsonBean {
    @SerializedName("rows")
    val rows: List<Row> = listOf()

    @SerializedName("total")
    val total: Int = 0


    data class Row(
        @SerializedName("bz")
        val bz: String = "",
        @SerializedName("cjbzmc")
        val cjbzmc: String = "",
        @SerializedName("cjdm")
        val cjdm: String = "",
        @SerializedName("cjfsmc")
        val cjfsmc: String = "",
        @SerializedName("cjjd")
        val cjjd: String = "",
        @SerializedName("isactive")
        val isactive: String = "",
        @SerializedName("kcbh")
        val kcbh: String = "",
        @SerializedName("kcdlmc")
        val kcdlmc: String = "",
        @SerializedName("kcdm")
        val kcdm: String = "",
        @SerializedName("kcflmc")
        val kcflmc: String = "",
        @SerializedName("kcmc")
        val kcmc: String = "",
        @SerializedName("kcrwdm")
        val kcrwdm: String = "",
        @SerializedName("kkbmmc")
        val kkbmmc: String = "",
        @SerializedName("ksxzdm")
        val ksxzdm: String = "",
        @SerializedName("ksxzmc")
        val ksxzmc: String = "",
        @SerializedName("rownum_")
        val rownum: String = "",
        @SerializedName("rwdm")
        val rwdm: String = "",
        @SerializedName("wpj")
        val wpj: String = "",
        @SerializedName("wpjbz")
        val wpjbz: String = "",
        @SerializedName("wzc")
        val wzc: String = "",
        @SerializedName("wzcbz")
        val wzcbz: String = "",
        @SerializedName("xdfsmc")
        val xdfsmc: String = "",
        @SerializedName("xf")
        val xf: String = "",
        @SerializedName("xnxqdm")
        val xnxqdm: String = "",
        @SerializedName("xnxqmc")
        val xnxqmc: String = "",
        @SerializedName("xsbh")
        val xsbh: String = "",
        @SerializedName("xsdm")
        val xsdm: String = "",
        @SerializedName("xsxm")
        val xsxm: String = "",
        @SerializedName("zcj")
        val zcj: String = "",
        @SerializedName("zcjfs")
        val zcjfs: String = "",
        @SerializedName("zxs")
        val zxs: String = ""
    )
}
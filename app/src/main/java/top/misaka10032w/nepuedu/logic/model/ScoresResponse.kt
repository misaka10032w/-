package top.misaka10032w.nepuedu.logic.model

import com.google.gson.annotations.SerializedName

data class ScoresResponse(
    val rows: List<Row>,
    val total: Int
)

data class Row(
    val bz: String,
    val cjbzmc: String,
    val cjdm: String,
    val cjfsmc: String,
    val cjjd: String,
    val isactive: String,
    val kcbh: String,
    val kcdlmc: String,
    val kcdm: String,
    val kcflmc: String,
    val kcmc: String,
    val kcrwdm: String,
    val kkbmmc: String,
    val ksxzdm: String,
    val ksxzmc: String,
    val rownum_: String,
    val rwdm: String,
    val wpj: String,
    val wpjbz: String,
    val wzc: String,
    val wzcbz: String,
    val xdfsmc: String,
    val xf: String,
    val xnxqdm: String,
    val xnxqmc: String,
    val xsbh: String,
    val xsdm: String,
    val xsxm: String,
    val zcj: String,
    val zcjfs: String,
    val zxs: String
)
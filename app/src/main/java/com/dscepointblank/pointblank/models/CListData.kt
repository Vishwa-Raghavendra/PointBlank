package com.dscepointblank.pointblank.models


import com.google.gson.annotations.SerializedName

data class CListData(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("objects")
    val contestList: List<Object>
)
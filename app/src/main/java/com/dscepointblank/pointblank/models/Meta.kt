package com.dscepointblank.pointblank.models


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("total_count")
    val totalCount: Int
)
package com.dscepointblank.pointblank.models


import com.google.gson.annotations.SerializedName

data class Resource(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
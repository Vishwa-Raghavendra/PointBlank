package com.dscepointblank.pointblank.models


import com.google.gson.annotations.SerializedName

data class Object(
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("end")
    val end: String,
    @SerializedName("event")
    val event: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("resource")
    val resource: Resource,
    @SerializedName("start")
    val start: String
)
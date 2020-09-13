package com.dscepointblank.pointblank.models

data class FormattedEvents(
    val uid: Long = 0,
    val eventName: String = "",
    val eventLink: String = "",
    val eventHost:String = "",
    val startDate:String ="",
    val endDate:String ="",
    val eventHostID :Int=0,
    val eventDuration:Long=0,
    val eventColor :Int=0
) {

}
package com.dscepointblank.pointblank.utilityClasses

import com.dscepointblank.pointblank.models.FormattedEvents
import com.dscepointblank.pointblank.models.Object
import java.text.SimpleDateFormat
import java.util.*

class Mapper {
    companion object {
        fun mapCListToFormattedEvents(clistData: Object): FormattedEvents {

            return FormattedEvents(
                clistData.id,
                clistData.event,
                clistData.href,
                Constants.CONTEST_WEBSITE_NAME[clistData.resource.id]!!,
                Constants.toSDF.format(Constants.fromSDF.parse(clistData.start)!!),
                Constants.toSDF.format(Constants.fromSDF.parse(clistData.end)!!),
                clistData.resource.id,
                clistData.duration,
                Constants.CONTEST_WEBSITE_COLOR[clistData.resource.id]!!
            )
        }
    }
}
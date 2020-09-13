package com.dscepointblank.pointblank.viewmodels.fragViewModels

import androidx.lifecycle.*
import com.dscepointblank.pointblank.models.FormattedEvents
import com.dscepointblank.pointblank.repositories.HomeScreenFragRepository
import com.dscepointblank.pointblank.utilityClasses.Constants
import com.dscepointblank.pointblank.utilityClasses.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class HomeScreenFragViewModel(private val homeScreenFragRepository: HomeScreenFragRepository) :
    ViewModel() {

    init {
        getContests()
    }

    val allEvents = Transformations.switchMap(homeScreenFragRepository.allEvents) {
        liveData<Resource<ArrayList<FormattedEvents>>>(Dispatchers.IO) {
            emit(sortList(it))
        }
    }


    private fun sortList(it: ArrayList<FormattedEvents>): Resource<ArrayList<FormattedEvents>> {
        val prevData: ArrayList<FormattedEvents> = ArrayList()
        if (!allEvents.value?.data.isNullOrEmpty()) {
            prevData.addAll(allEvents.value!!.data!!)
        }

        prevData.addAll(it)
        prevData.sortBy {
            Constants.toSDF.parse(it.startDate)!!.time
        }
        return Resource.Success(prevData)
    }


    private fun getContests() {
        viewModelScope.launch(Dispatchers.IO) {
            homeScreenFragRepository.getEvents()
        }
    }

    fun getShareableEventData(formattedEvents: FormattedEvents): String {
        val body =
            "Event Details are\nName: ${formattedEvents.eventName}\nLink: ${formattedEvents.eventLink}\nHost : ${formattedEvents.eventHost}\nStartTime: ${formattedEvents.startDate}\nEndTime: ${formattedEvents.endDate}"

        return body
    }
}
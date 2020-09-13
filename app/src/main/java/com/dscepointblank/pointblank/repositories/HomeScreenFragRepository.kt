package com.dscepointblank.pointblank.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dscepointblank.pointblank.models.FormattedEvents
import com.dscepointblank.pointblank.utilityClasses.Constants
import com.dscepointblank.pointblank.utilityClasses.Mapper
import com.dscepointblank.pointblank.utilityClasses.RetrofitInstance
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class HomeScreenFragRepository {


    private val _allEvents = MutableLiveData<ArrayList<FormattedEvents>>()
    val allEvents: LiveData<ArrayList<FormattedEvents>>
        get() = _allEvents


    private suspend fun getClistData() {
        val clistData = RetrofitInstance.clistAPI.getContests()

        if (clistData.isSuccessful) {
            val formattedEvents = ArrayList<FormattedEvents>()

            val eventList = clistData.body()!!.contestList
            val todayDate = Calendar.getInstance(Locale.UK).timeInMillis
            eventList.forEach {
                val endEventTime: Long = Constants.fromSDF.parse(it.end)!!.time
                if (endEventTime > todayDate)
                    formattedEvents.add(Mapper.mapCListToFormattedEvents(it))
            }
            _allEvents.postValue(formattedEvents)
        }
    }

    suspend fun getEvents() {
        getClistData()
        getFirebaseData()
    }

    private fun getFirebaseData() {
        Firebase.firestore.collection("DEMO").addSnapshotListener { value, error ->
            if(error!=null||value==null)
                return@addSnapshotListener

            val x = ArrayList<FormattedEvents>()
            for(document in value.documents)
            {
                x.add(document.toObject(FormattedEvents::class.java)!!)
            }
            _allEvents.postValue(x)
        }
    }
}
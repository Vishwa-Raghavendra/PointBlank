package com.dscepointblank.pointblank.viewmodels.fragViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscepointblank.pointblank.models.CListData
import com.dscepointblank.pointblank.repositories.HomeScreenFragRepository
import com.dscepointblank.pointblank.utilityClasses.Resource
import com.dscepointblank.pointblank.utilityClasses.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeScreenFragViewModel(val homeScreenFragRepository: HomeScreenFragRepository) :
    ViewModel() {


    val  clistContests  : MutableLiveData<Resource<CListData>> = MutableLiveData()


    fun getContests()
    {
        viewModelScope.launch(Dispatchers.IO) {
            clistContests.postValue(Resource.Loading())
            val response = homeScreenFragRepository.getEventsFromClist()
            clistContests.postValue(handleContestsResponse(response))
        }
    }

    private fun handleContestsResponse(response: Response<CListData>) : Resource<CListData> {

        if(response.isSuccessful)
        {
            response.body()?.let {responseData ->
                return Resource.Success(responseData)
            }
        }
        return  Resource.Error(message = response.message())
    }
}
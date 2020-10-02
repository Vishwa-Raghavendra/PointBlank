package com.dscepointblank.pointblank.viewmodels.fragViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscepointblank.pointblank.models.cfrating.CfRatingResponse
import com.dscepointblank.pointblank.repositories.CfRatingFragRepository
import com.dscepointblank.pointblank.utilityClasses.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CfRatingFragViewModel(
    val repository: CfRatingFragRepository
): ViewModel() {
    val cfRatingLiveData : MutableLiveData<Resource<CfRatingResponse>> = MutableLiveData()


    fun getCfUserRatingChange(codeForceHandle : String) = viewModelScope.launch {
        cfRatingLiveData.postValue(Resource.Loading())
        val response = repository.getCfUserRatingChange(codeForceHandle = codeForceHandle)
        cfRatingLiveData.postValue(handleCfRatingChange(response))
    }

    private fun handleCfRatingChange(response : Response<CfRatingResponse>) : Resource<CfRatingResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }
}
package com.dscepointblank.pointblank.repositories
import com.dscepointblank.pointblank.models.CListData
import com.dscepointblank.pointblank.utilityClasses.RetrofitInstance
import retrofit2.Response

class HomeScreenFragRepository {

    suspend fun getEventsFromClist(): Response<CListData> = RetrofitInstance.clistAPI.getContests()
}
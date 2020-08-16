package com.dscepointblank.pointblank.apis

import com.dscepointblank.pointblank.models.CListData
import com.dscepointblank.pointblank.utilityClasses.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CListAPI {
    @GET("/api/v1/contest/")
    suspend fun getContests(
        @Query("username") userName: String = "PP",
        @Query("api_key") apiKey: String = Constants.CLIST_API_KEY,
        @Query("limit") limit: Int = 100,
        @Query("start__gte")startDate :String = Constants.getDate(),
        @Query("resource__id__in")id:Array<Int> = Constants.CONTEST_ARRAY
    ): Response<CListData>
}
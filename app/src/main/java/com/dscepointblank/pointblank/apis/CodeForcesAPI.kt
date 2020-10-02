package com.dscepointblank.pointblank.apis

import com.dscepointblank.pointblank.models.CodeForce_UserDetails
import com.dscepointblank.pointblank.models.cfrating.CfRatingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CodeForcesAPI
{

    @GET("api/user.info")
    suspend fun getUserDetails(@Query(value = "handles")codeForcesUserID:String) :CodeForce_UserDetails

    @GET
    suspend fun getUserRatingChange(
        @Url
        url : String = "https://codeforces.com/api/user.rating",
        @Query("handle")
        codeForceHandle: String
    ) : Response<CfRatingResponse>
}
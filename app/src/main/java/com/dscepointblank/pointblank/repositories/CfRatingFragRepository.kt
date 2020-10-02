package com.dscepointblank.pointblank.repositories

import com.dscepointblank.pointblank.utilityClasses.RetrofitInstance

class CfRatingFragRepository {
    suspend fun getCfUserRatingChange(codeForceHandle : String)=
        RetrofitInstance.codeForcesAPI.getUserRatingChange(codeForceHandle = codeForceHandle)
}
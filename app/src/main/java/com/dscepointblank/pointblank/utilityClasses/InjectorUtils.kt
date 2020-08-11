package com.dscepointblank.pointblank.utilityClasses

import com.dscepointblank.pointblank.factories.HomeScreenFragFactory
import com.dscepointblank.pointblank.repositories.HomeScreenFragRepository

object InjectorUtils {
    fun provideHomeScreenViewModelFactory(): HomeScreenFragFactory {
        val repository = HomeScreenFragRepository()
        return HomeScreenFragFactory(repository)
    }
}
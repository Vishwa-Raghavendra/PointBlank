package com.dscepointblank.pointblank.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dscepointblank.pointblank.repositories.HomeScreenFragRepository
import com.dscepointblank.pointblank.viewmodels.fragViewModels.HomeScreenFragViewModel

@Suppress("UNCHECKED_CAST")
class HomeScreenFragFactory(private val homeScreenFragRepository: HomeScreenFragRepository) :ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return HomeScreenFragViewModel(homeScreenFragRepository) as T
    }
}
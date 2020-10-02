package com.dscepointblank.pointblank.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dscepointblank.pointblank.repositories.CfRatingFragRepository
import com.dscepointblank.pointblank.viewmodels.fragViewModels.CfRatingFragViewModel

class CfRatingFragFactory(
    val repository: CfRatingFragRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CfRatingFragViewModel(repository) as T
    }
}
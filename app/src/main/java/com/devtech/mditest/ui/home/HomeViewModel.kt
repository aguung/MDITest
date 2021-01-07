package com.devtech.mditest.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.devtech.mditest.data.TestDao
import kotlinx.coroutines.flow.*

class HomeViewModel @ViewModelInject constructor(
    private val testDao: TestDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val category = testDao.getCategory().asLiveData()

    val countCart = testDao.getCartCount().asLiveData()

    val categoryType = state.getLiveData("category", "")

    private val productFlow = categoryType.asFlow().map {
        it.toInt()
    }.flatMapLatest { testDao.getCategoryWithProduct(it) }

    val productByType = productFlow.asLiveData()
}
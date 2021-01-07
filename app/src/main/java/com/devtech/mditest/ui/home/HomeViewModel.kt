package com.devtech.mditest.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.devtech.mditest.data.TestDao
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HomeViewModel @ViewModelInject constructor(
    private val testDao: TestDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val category = testDao.getCategory().asLiveData()

    val categoryType = state.getLiveData("category", "")

    private val productFlow = flowOf(categoryType.asFlow().map {
        testDao.getCategoryWithProduct(it.toInt())
    })

    val productByType = productFlow.asLiveData()
}
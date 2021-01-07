package com.devtech.mditest.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.devtech.mditest.data.TestDao
import com.devtech.mditest.data.entity.Cart
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(
    private val testDao: TestDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val categoryType = state.getLiveData("category", "")

    private val productFlow = categoryType.asFlow().map {
        it.toInt()
    }.flatMapLatest { testDao.getCategoryWithProduct(it) }

    val productByType = productFlow.asLiveData()

    fun addCart(cart: Cart) = viewModelScope.launch {
        testDao.insertCart(cart)
    }

}
package com.devtech.mditest.ui.cart

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.devtech.mditest.data.TestDao
import kotlinx.coroutines.flow.map

class CartViewModel @ViewModelInject constructor(
    private val testDao: TestDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val cart = testDao.getProductAndCart().map {
        it.filter { data -> data.cart !== null }
    }.asLiveData()
}
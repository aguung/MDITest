package com.devtech.mditest.ui.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.devtech.mditest.data.TestDao

class DetailViewModel @ViewModelInject constructor(
    private val testDao: TestDao,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

}
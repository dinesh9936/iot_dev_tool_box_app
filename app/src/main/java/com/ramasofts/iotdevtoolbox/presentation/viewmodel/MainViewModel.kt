package com.ramasofts.iotdevtoolbox.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var isAppReady by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            // Simulate real work
            delay(2000)
            isAppReady = true
        }
    }
}
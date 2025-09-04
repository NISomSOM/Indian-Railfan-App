package com.example.indianrailfanapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.indianrailfanapp.data.Locomotive
import com.example.indianrailfanapp.data.locoService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _locoState = mutableStateOf(LocoState())
    val locoState: State<LocoState> = _locoState


    init {
        fetchLocomotives()
    }

    private fun fetchLocomotives() {
        viewModelScope.launch {
            try {
                val response = locoService.getLocos()
                _locoState.value = _locoState.value.copy(
                    list = response.locos,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _locoState.value = _locoState.value.copy(
                    loading = false,
                    error = "Error fetching categories"
                )
            }
        }
    }
    data class LocoState(
        val loading: Boolean = true,
        val list: List<Locomotive> = emptyList(),
        val error: String? = null
    )
}

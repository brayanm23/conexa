package com.conexa.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.cart.model.CartResponse

class CartViewModel : ViewModel() {

    private val _uiState = MutableLiveData<CartUiState>()
    val uiState: LiveData<CartUiState>
        get() = _uiState

    /*
    var settingRepository = SettingRepository()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = SettingsUiState.ConnectionError
    }

    init {
        getSettings()
    }

    private fun showLoading() {
        _uiState.value = SettingsUiState.Loading
    }

    fun getSettings() {
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            showLoading()

            val response = settingRepository.getSettings()

            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { data ->
                        _uiState.value = SettingsUiState.Success(data)
                    }
                }
                Status.SERVER_ERROR -> {
                    _uiState.value = SettingsUiState.ServerError
                }
                Status.CONNECTION_ERROR -> {
                    _uiState.value = SettingsUiState.ConnectionError
                }
            }
        }
    }
     */

    sealed class CartUiState {
        object Loading : CartUiState()
        object ServerError : CartUiState()
        object ConnectionError : CartUiState()
        data class Success(val data: CartResponse) : CartUiState()
    }

}
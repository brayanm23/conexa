package com.conexa.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.catalog.model.CatalogResponse

class CatalogViewModel : ViewModel() {

    private val _uiState = MutableLiveData<CatalogUiState>()
    val uiState: LiveData<CatalogUiState>
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

    sealed class CatalogUiState {
        object Loading : CatalogUiState()
        object ServerError : CatalogUiState()
        object ConnectionError : CatalogUiState()
        data class Success(val data: CatalogResponse) : CatalogUiState()
    }

}
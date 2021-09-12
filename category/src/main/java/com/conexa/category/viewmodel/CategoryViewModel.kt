package com.conexa.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.category.model.CategoryResponse

class CategoryViewModel : ViewModel() {

    private val _uiState = MutableLiveData<CategoryUiState>()
    val uiState: LiveData<CategoryUiState>
        get() = _uiState

    sealed class CategoryUiState {
        object Loading : CategoryUiState()
        object ServerError : CategoryUiState()
        object ConnectionError : CategoryUiState()
        data class Success(val data: CategoryResponse) : CategoryUiState()
    }

}
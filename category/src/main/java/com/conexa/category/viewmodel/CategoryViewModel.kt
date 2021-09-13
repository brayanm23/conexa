package com.conexa.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.category.domain.ShowCategoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryViewModel : ViewModel() {

    private val showCategoriesUseCase = ShowCategoriesUseCase()

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<CategoryUiState>()
    val uiState: LiveData<CategoryUiState>
        get() = _uiState

    fun getCategories() {
        disposables.add(showCategoriesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CategoryUiState.Loading) }
            .subscribe(
                { response -> _uiState.setValue(CategoryUiState.Success(response)) },
                { throwable -> _uiState.setValue(CategoryUiState.Error) }
            )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

    sealed class CategoryUiState {
        object Loading : CategoryUiState()
        object Error : CategoryUiState()
        data class Success(val data: List<String>) : CategoryUiState()
    }

}
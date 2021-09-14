package com.conexa.filter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.filter.domain.ShowCategoriesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryViewModel : ViewModel() {

    private val showCategoriesUseCase = ShowCategoriesUseCase()

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<CategoryUiState>()
    val uiState: LiveData<CategoryUiState>
        get() = _uiState

    private val _filter = MutableLiveData<String?>(null)
    val filter: LiveData<String?>
        get() = _filter

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

    fun saveCategoryForFilter(category: String) {
        _filter.postValue(category)
    }

    fun deleteCategoryForFilter() {
        _filter.postValue(null)
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
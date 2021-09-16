package com.conexa.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.conexa.cart.repository.database.AppDatabase

class ViewModelFactory(private val dbHelper: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CartViewModel(dbHelper) as T
    }
}
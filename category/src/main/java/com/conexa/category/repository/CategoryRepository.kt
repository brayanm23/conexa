package com.conexa.category.repository

import com.conexa.category.model.CategoryResponse
import com.conexa.category.repository.api.CategoryApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryRepository {

    var catalogapi = RetrofitBuilder.getClient("").create(CategoryApi::class.java)

    fun getCatalog(): Result<CategoryResponse> =
        catalogapi.getCategories()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> return@subscribe Result.success(value) },
                { error -> return@subscribe Result.serverError() }
            )
}
package com.conexa.catalog.repository

import com.conexa.catalog.model.CatalogResponse
import com.conexa.catalog.repository.api.CatalogApi
import com.conexa.networking.RetrofitBuilder
import com.conexa.networking.status.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CatalogRepository {

    var catalogapi = RetrofitBuilder.getClient("").create(CatalogApi::class.java)

    fun getCatalog(): Result<CatalogResponse> =
        catalogapi.getProducts()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> return@subscribe Result.success(value) },
                { error -> return@subscribe Result.serverError() }
            )
}
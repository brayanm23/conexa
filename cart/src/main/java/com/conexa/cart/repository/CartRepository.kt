package com.conexa.cart.repository

import com.conexa.cart.model.CartResponse
import com.conexa.cart.repository.api.CartApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartRepository {

    var catalogapi = RetrofitBuilder.getClient("").create(CartApi::class.java)

    fun getCatalog(): Result<CartResponse> =
        catalogapi.getProducts()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> return@subscribe Result.success(value) },
                { error -> return@subscribe Result.serverError() }
            )
}
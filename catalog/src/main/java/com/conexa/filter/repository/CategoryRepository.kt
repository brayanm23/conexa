package com.conexa.filter.repository

import com.conexa.filter.repository.api.CategoryApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.Single

class CategoryRepository {

    var catalogapi = RetrofitBuilder.getClient("https://fakestoreapi.com/").create(CategoryApi::class.java)

    fun getCatalog(): Single<List<String>> = catalogapi.getCategories()
}
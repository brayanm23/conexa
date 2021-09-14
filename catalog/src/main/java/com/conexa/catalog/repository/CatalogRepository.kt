package com.conexa.catalog.repository

import com.conexa.catalog.model.Product
import com.conexa.catalog.repository.api.CatalogApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.Single

class CatalogRepository {

    var catalogapi = RetrofitBuilder.getClient("https://fakestoreapi.com/").create(CatalogApi::class.java)

    fun getCatalog(): Single<List<Product>> = catalogapi.getProducts()

    fun getCatalogApplyFilter(category: String): Single<List<Product>> = catalogapi.getProductsSpecificCategory(category)
}
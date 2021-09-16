package com.conexa.catalog.domain

import com.conexa.cart.model.Product
import com.conexa.catalog.repository.CatalogRepository
import io.reactivex.Single

class ShowCatalogUseCase {

    var catalogRepository = CatalogRepository()

    fun execute(): Single<List<Product>> = catalogRepository.getCatalog()

}
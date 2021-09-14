package com.conexa.catalog.domain

import com.conexa.catalog.model.Product
import com.conexa.catalog.repository.CatalogRepository
import io.reactivex.Single

class ShowCatalogApplyFilterUseCase {

    var catalogRepository = CatalogRepository()

    fun execute(category: String): Single<List<Product>> = catalogRepository.getCatalogApplyFilter(category)

}
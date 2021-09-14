package com.conexa.filter.domain

import com.conexa.filter.repository.CategoryRepository
import io.reactivex.Single

class ShowCategoriesUseCase {

    var categoryRepository = CategoryRepository()

    fun execute(): Single<List<String>> {
        return categoryRepository.getCatalog()
    }

}
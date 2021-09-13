package com.conexa.category.domain

import com.conexa.category.repository.CategoryRepository
import io.reactivex.Single

class ShowCategoriesUseCase {

    var categoryRepository = CategoryRepository()

    fun execute(): Single<List<String>> {
        return categoryRepository.getCatalog()
    }

}
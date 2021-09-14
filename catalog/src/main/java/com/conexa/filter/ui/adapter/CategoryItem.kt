package com.conexa.filter.ui.adapter

import android.view.View
import com.conexa.catalog.R
import com.conexa.catalog.databinding.CategoryItemAdapterBinding
import com.xwray.groupie.viewbinding.BindableItem

class CategoryItem (
    private val category: String,
    private val listener: OnClickItemListener
) : BindableItem<CategoryItemAdapterBinding>() {

    override fun getLayout() = R.layout.category_item_adapter

    override fun bind(viewBinding: CategoryItemAdapterBinding, position: Int) {
        viewBinding.container.setOnClickListener { listener.onItemClick(category) }
        viewBinding.category.text = category
    }

    override fun initializeViewBinding(view: View): CategoryItemAdapterBinding {
        return CategoryItemAdapterBinding.bind(view)
    }

    interface OnClickItemListener {
        fun onItemClick(category: String)
    }
}
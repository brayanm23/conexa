package com.conexa.catalog.ui.adapter

import android.view.View
import com.conexa.catalog.R
import com.conexa.catalog.databinding.ProductItemAdapterBinding
import com.conexa.cart.model.Product
import com.xwray.groupie.viewbinding.BindableItem

data class ProductItem(private val product: Product, private val listener: OnClickListener) : BindableItem<ProductItemAdapterBinding>() {

    override fun getLayout() = R.layout.product_item_adapter

    override fun bind(viewBinding: ProductItemAdapterBinding, position: Int) {
        with(viewBinding) {
            image.setImageURI(product.image)
            title.text = product.title
            price.text = product.price
            addCart.setOnClickListener { listener.onClickAddInCart(product.id) }
        }
    }

    override fun initializeViewBinding(view: View): ProductItemAdapterBinding {
        return ProductItemAdapterBinding.bind(view)
    }

    interface OnClickListener {
        fun onClickAddInCart(id: Int)
    }
}
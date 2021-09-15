package com.conexa.cart.ui.adapter

import android.view.View
import com.conexa.cart.R
import com.conexa.cart.databinding.CartItemAdapterBinding
import com.conexa.cart.model.Product
import com.xwray.groupie.viewbinding.BindableItem

data class CartItem(private val product: Product) : BindableItem<CartItemAdapterBinding>() {

    override fun getLayout() = R.layout.cart_item_adapter

    override fun bind(viewBinding: CartItemAdapterBinding, position: Int) {
        with(viewBinding) {
            image.setImageURI(product.image)
            title.text = product.title
            amount.text = product.quantity.toString()
            //price.text = product.price.toString()
        }
    }

    override fun initializeViewBinding(view: View): CartItemAdapterBinding {
        return CartItemAdapterBinding.bind(view)
    }
}
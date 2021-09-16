package com.conexa.cart.ui.adapter

import android.view.View
import com.conexa.cart.R
import com.conexa.cart.databinding.CartItemAdapterBinding
import com.conexa.cart.model.Product
import com.xwray.groupie.viewbinding.BindableItem

data class CartItem(private val product: Product, private val listener: OnClickListener) : BindableItem<CartItemAdapterBinding>() {

    override fun getLayout() = R.layout.cart_item_adapter

    override fun bind(viewBinding: CartItemAdapterBinding, position: Int) {
        with(viewBinding) {
            image.setImageURI(product.image)
            title.text = product.title
            amount.text = product.quantity.toString()
            price.text = product.price
            delete.setOnClickListener { listener.onDeleteClick(product.id) }
            add.setOnClickListener { listener.onUpdateClick(product.id, product.quantity + 1) }
            subtract.setOnClickListener { listener.onUpdateClick(product.id, product.quantity - 1) }
        }
    }

    override fun initializeViewBinding(view: View): CartItemAdapterBinding {
        return CartItemAdapterBinding.bind(view)
    }

    interface OnClickListener {
        fun onDeleteClick(id: Int)
        fun onUpdateClick(id: Int, quantity: Int)
    }
}
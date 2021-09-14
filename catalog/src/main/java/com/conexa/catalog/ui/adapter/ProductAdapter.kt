package com.conexa.catalog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conexa.catalog.databinding.ProductItemAdapterBinding
import com.conexa.catalog.databinding.ProductItemAdapterBinding.inflate
import com.conexa.catalog.model.Product

class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductItemViewHolder(inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(products[position])
    }

    class ProductItemViewHolder(private val binding: ProductItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                image.setImageURI(product.image)
                title.text = product.title
                price.text = product.price.toString()
            }
        }
    }
}

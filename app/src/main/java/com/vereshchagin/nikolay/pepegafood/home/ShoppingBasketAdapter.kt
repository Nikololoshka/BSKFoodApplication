package com.vereshchagin.nikolay.pepegafood.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vereshchagin.nikolay.pepegafood.R

class ShoppingBasketAdapter: RecyclerView.Adapter<ShoppingBasketItemHolder>() {

    private val items = listOf("500 р", "1000 р", "2500р", "5000р")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBasketItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_basket, parent, false)
        return ShoppingBasketItemHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShoppingBasketItemHolder, position: Int) {
        holder.bind(items[position])
    }
}
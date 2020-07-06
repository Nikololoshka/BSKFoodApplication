package com.vereshchagin.nikolay.pepegafood.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vereshchagin.nikolay.pepegafood.databinding.ItemShoppingBasketBinding

class ShoppingBasketItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemShoppingBasketBinding.bind(itemView)

    fun bind(text: String) {
        binding.foodBasketCost.text = text
    }
}
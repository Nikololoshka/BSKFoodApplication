package com.vereshchagin.nikolay.pepegafood.home.paging.favorite

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vereshchagin.nikolay.pepegafood.databinding.ItemFavoriteBasketBinding
import com.vereshchagin.nikolay.pepegafood.home.repository.model.FavoriteBasket

class FavoriteBasketItemHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemFavoriteBasketBinding.bind(itemView)

    fun bind(basket: FavoriteBasket?) {
        binding.foodBasketTitle.text = basket?.title
        binding.foodBasketCost.text = basket?.cost
    }
}
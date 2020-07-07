package com.vereshchagin.nikolay.pepegafood.home.paging.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.vereshchagin.nikolay.pepegafood.R
import com.vereshchagin.nikolay.pepegafood.home.repository.model.ShoppingBasket


class ShoppingBasketAdapter
    : PagedListAdapter<ShoppingBasket, ShoppingBasketItemHolder>(
    BASKET_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBasketItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_basket, parent, false)
        return ShoppingBasketItemHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ShoppingBasketItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        /**
         * Компаратор для сравнения авто-корзин.
         */
        val BASKET_COMPARATOR = object : DiffUtil.ItemCallback<ShoppingBasket>() {

            override fun areItemsTheSame(oldItem: ShoppingBasket, newItem: ShoppingBasket) =
                oldItem.text == newItem.text

            override fun areContentsTheSame(oldItem: ShoppingBasket, newItem: ShoppingBasket) =
                oldItem.text == newItem.text
        }
    }
}
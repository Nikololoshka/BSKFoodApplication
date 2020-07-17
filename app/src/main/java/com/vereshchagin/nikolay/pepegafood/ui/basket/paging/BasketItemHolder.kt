package com.vereshchagin.nikolay.pepegafood.ui.basket.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vereshchagin.nikolay.pepegafood.databinding.ItemBasketBinding
import com.vereshchagin.nikolay.pepegafood.model.BasketItem

/**
 * Holder элемента корзины в списке.
 */
class BasketItemHolder (
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemBasketBinding.bind(itemView)

    /**
     * Присоединяет данные к holder'у.
     */
    fun bind(basket: BasketItem?) {

    }
}
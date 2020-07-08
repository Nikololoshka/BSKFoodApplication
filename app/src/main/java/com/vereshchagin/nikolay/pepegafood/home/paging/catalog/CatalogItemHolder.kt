package com.vereshchagin.nikolay.pepegafood.home.paging.catalog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vereshchagin.nikolay.pepegafood.databinding.ItemCatalogBinding
import com.vereshchagin.nikolay.pepegafood.home.repository.model.CatalogItem

/**
 * Holder элемента каталога в списке.
 */
class CatalogItemHolder (
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCatalogBinding.bind(itemView)

    /**
     * Присоединяет данные к holder'у.
     */
    fun bind(catalog: CatalogItem?) {
        binding.categoryName.text = catalog?.title
    }
}
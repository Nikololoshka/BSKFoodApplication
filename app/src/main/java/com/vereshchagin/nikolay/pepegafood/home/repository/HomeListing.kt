package com.vereshchagin.nikolay.pepegafood.home.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vereshchagin.nikolay.pepegafood.home.repository.model.CatalogItem
import com.vereshchagin.nikolay.pepegafood.home.repository.model.FavoriteBasket
import com.vereshchagin.nikolay.pepegafood.home.repository.model.ShoppingBasket

class HomeListing (
    val shoppingBaskets: LiveData<PagedList<ShoppingBasket>>,
    val favoriteBaskets: LiveData<PagedList<FavoriteBasket>>,
    val catalogItems: LiveData<PagedList<CatalogItem>>
)
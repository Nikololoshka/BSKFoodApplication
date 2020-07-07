package com.vereshchagin.nikolay.pepegafood.home.repository.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vereshchagin.nikolay.pepegafood.home.repository.model.CatalogItem
import com.vereshchagin.nikolay.pepegafood.home.repository.model.FavoriteBasket
import com.vereshchagin.nikolay.pepegafood.home.repository.model.ShoppingBasket

/**
 * Интерфейс для работы с главной страницой.
 */
@Dao
interface HomeDao {

    /**
     * Добавляет авто-корзины в БД.
     * @param baskets список корзин.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingBaskets(baskets: List<ShoppingBasket>)

    /**
     * Возвращает список (DataSource) авто-корзин.
     */
    @Query("SELECT * FROM shopping_basket ORDER BY id ASC")
    fun shoppingBaskets() : DataSource.Factory<Int, ShoppingBasket>

    /**
     * Добавляет избранные корзины в БД.
     * @param baskets список корзин.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteBaskets(baskets: List<FavoriteBasket>)

    /**
     * Возвращает список (DataSource) избранных корзин.
     */
    @Query("SELECT * FROM favorite_basket ORDER BY id ASC")
    fun favoriteBaskets() : DataSource.Factory<Int, FavoriteBasket>

    /**
     * Добавляет элементы каталога в БД.
     * @param baskets список корзин.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatalogItems(baskets: List<CatalogItem>)

    /**
     * Возвращает список (DataSource) из элементов каталога.
     */
    @Query("SELECT * FROM catalog_item ORDER BY id ASC")
    fun catalogItems() : DataSource.Factory<Int, CatalogItem>
}
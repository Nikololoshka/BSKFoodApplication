package com.vereshchagin.nikolay.pepegafood.ui.home.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Объект каталога на главной странице.
 */
@Entity(tableName = "catalog_item")
class CatalogItem (
    @PrimaryKey
    val id: Int,
    val title: String
)
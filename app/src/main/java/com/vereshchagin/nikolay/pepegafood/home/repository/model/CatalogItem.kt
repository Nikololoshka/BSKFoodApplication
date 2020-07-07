package com.vereshchagin.nikolay.pepegafood.home.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog_item")
class CatalogItem (
    @PrimaryKey
    val id: Int,
    val title: String
)
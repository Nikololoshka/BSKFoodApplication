package com.vereshchagin.nikolay.pepegafood.home.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_basket")
class FavoriteBasket(
    @PrimaryKey
    val id: Int,
    val title: String,
    val cost: String
)
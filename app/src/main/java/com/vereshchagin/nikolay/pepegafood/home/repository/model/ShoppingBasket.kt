package com.vereshchagin.nikolay.pepegafood.home.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_basket")
class ShoppingBasket(
    @PrimaryKey
    val id: Int,
    val text: String
)
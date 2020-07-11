package com.vereshchagin.nikolay.pepegafood.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Ответ от сервера после проверки токена.
 */
class CheckTokenResponse (
    val tokenActive: Boolean,
    @SerializedName("username") val username: String
)
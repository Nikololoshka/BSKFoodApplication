package com.vereshchagin.nikolay.pepegafood.api.responses

import com.google.gson.annotations.SerializedName

/**
 * Ответ от сервера после входа.
 */
class LoginResponse (
    val token: String,
    val success: Boolean,
    @SerializedName("userName") val username: String
    // val errorsMessages
)
package com.vereshchagin.nikolay.pepegafood.data

/**
 * Пользовательские данные для взаимодействия с сервером.
 */
class UserData (
    val token: String,
    val username: String
) {
    val bearer get() = "bearer $token"

    fun isEmpty() = token.isEmpty() || username.isEmpty()
}
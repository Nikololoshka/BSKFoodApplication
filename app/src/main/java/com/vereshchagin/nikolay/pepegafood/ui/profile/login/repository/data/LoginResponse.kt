package com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository.data

/**
 * Ответ от сервера после входа.
 */
class LoginResponse (
    val token: String,
    val success: Boolean,
    val userName: String
    // val errorsMessages
)
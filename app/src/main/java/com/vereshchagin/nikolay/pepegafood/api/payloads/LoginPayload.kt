package com.vereshchagin.nikolay.pepegafood.api.payloads

/**
 * Данные для POST запроса входа.
 */
class LoginPayload (
    val email: String,
    val password: String
)
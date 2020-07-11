package com.vereshchagin.nikolay.pepegafood.api.payloads


/**
 * Данные для POST запроса регистрации.
 */
class RegistrationPayload (
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val address: String,
    val password: String
)
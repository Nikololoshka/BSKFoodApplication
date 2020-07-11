package com.vereshchagin.nikolay.pepegafood.api.payloads

/**
 * Данные для PUT запроса смены пароля.
 */
class ChangePasswordPayload (
    val oldPassword: String,
    val newPassword: String
)
package com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository.api

import com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API для авторизации / регистрации.
 */
interface LoginApi {

    @POST("/api/identity/login")
    fun login(@Body data: LoginData) : Call<LoginResponse>

    @POST("/api/identity/regist")
    fun registration(@Body data: RegistrationData)

    /**
     * Данные для POST запроса входа.
     */
    class LoginData (
        val email: String,
        val password: String
    )

    /**
     * Данные для POST запроса регистрации.
     */
    class RegistrationData (
        val email: String,
        val firstName: String,
        val lastNamr: String,
        val phoneNumber: String,
        val address: String,
        val password: String
    )
}
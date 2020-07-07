package com.vereshchagin.nikolay.pepegafood.profile.login.data

class RegistrationFormState (
    val userNameError: Int? = null,
    val emailError: Int? = null,
    val phoneError: Int? = null,
    val passwordError: Int? = null,
    val conformPasswordError: Int? = null,
    val isDataValid: Boolean = false
)
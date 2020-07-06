package com.vereshchagin.nikolay.pepegafood.profile.login.data

import android.view.View

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: View? = null,
    val error: Int? = null
)
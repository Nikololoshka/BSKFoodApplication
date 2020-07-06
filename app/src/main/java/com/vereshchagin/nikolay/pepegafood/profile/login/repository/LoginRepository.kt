package com.vereshchagin.nikolay.pepegafood.profile.login.repository

import com.vereshchagin.nikolay.pepegafood.profile.login.repository.data.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {

    /**
     * Кэш с пользователем.
     */
    var user: LoggedInUser? = null
        private set

    /**
     * Авторизовался ли пользователь.
     */
    val isLoggedIn get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null

    }

    fun login(username: String, password: String) {
        // handle login
        TODO()
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
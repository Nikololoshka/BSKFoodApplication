package com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository

import androidx.lifecycle.MutableLiveData
import com.vereshchagin.nikolay.pepegafood.BuildConfig
import com.vereshchagin.nikolay.pepegafood.data.UserData
import com.vereshchagin.nikolay.pepegafood.settings.ApplicationPreference
import com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository.api.LoginApi
import com.vereshchagin.nikolay.pepegafood.ui.profile.login.repository.data.LoginResponse
import com.vereshchagin.nikolay.pepegafood.utils.CommonUtils
import com.vereshchagin.nikolay.pepegafood.utils.LoadState
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Репозиторий для входа и регистрации пользователя.
 */

class LoginRepository {

    /**
     * API для работы с входом / регистрацией.
     */
    private val retrofit: Retrofit
    private val api: LoginApi

    init {
        val builder = Retrofit.Builder()
            .baseUrl(CommonUtils.API_URL)
            .addConverterFactory(GsonConverterFactory.create())

        // включение лога
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            builder.client(client)
        }

        retrofit = builder.build()
        api = retrofit.create(LoginApi::class.java)
    }

    /**
     * Отправляет запрос на авторизацию пользователя к серверу.
     */
    fun login(
        email: String,
        password: String,
        loadingState: MutableLiveData<LoadState>
    ) {
        loadingState.value = LoadState.LOADING
        api.login(LoginApi.LoginData(email, password)).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginResponse(response, loadingState)
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loadingState.value = LoadState.error(t.message ?: "Unknown error")
            }
        })
    }

    /**
     * Отправляет запрос на регистрацию пользователя к серверу.
     */
    fun registration(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        password: String,
        loadingState: MutableLiveData<LoadState>
    ) {
        TODO("Нет реализации регистрации пользователя")
    }

    /**
     * Обрабатывает пришедший ответ ахавторизации от сервера.
     */
    private fun loginResponse(response: Response<LoginResponse>, loadingState: MutableLiveData<LoadState>) {
        if (!response.isSuccessful) {
            loadingState.value = LoadState.error(response.errorBody()?.string() ?: "Unknown error")
            return
        }

        val loginResponse = response.body()!!
        ApplicationPreference.setUserData(UserData(loginResponse.token, loginResponse.userName))

        loadingState.value = LoadState.LOADED
    }
}
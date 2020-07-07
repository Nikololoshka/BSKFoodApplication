package com.vereshchagin.nikolay.pepegafood.profile.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.vereshchagin.nikolay.pepegafood.R
import com.vereshchagin.nikolay.pepegafood.profile.login.data.LoginFormState
import com.vereshchagin.nikolay.pepegafood.profile.login.data.LoginResult
import com.vereshchagin.nikolay.pepegafood.profile.login.data.RegistrationFormState
import com.vereshchagin.nikolay.pepegafood.profile.login.repository.LoginRepository

/**
 *
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _registrationForm = MutableLiveData<RegistrationFormState>()
    val registrationFormState: LiveData<RegistrationFormState> = _registrationForm

    //******************
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    /**
     *
     */
    fun login(email: String, password: String) {
        loginRepository.login(email, password)

    }

    /**
     *
     */
    fun loginDataChanged(email: String, password: String) {
        when {
            !isEmailValid(email) -> {
                _loginForm.value =
                    LoginFormState(
                        emailError = R.string.login_email_invalid
                    )
            }
            !isPasswordValid(password) -> {
                _loginForm.value =
                    LoginFormState(
                        passwordError = R.string.login_password_invalid
                    )
            }
            else -> {
                _loginForm.value =
                    LoginFormState(
                        isDataValid = true
                    )
            }
        }
    }

    fun registrationDataChanged(userName: String, email: String, phone: String,
                                password: String, confirmPassword: String) {
        when {
            !isUserNameValid(userName) -> {
                _registrationForm.value =
                    RegistrationFormState(
                        userNameError = R.string.login_user_name_invalid
                    )
            }
            !isEmailValid(email) -> {
                _registrationForm.value =
                    RegistrationFormState(
                        emailError = R.string.login_email_invalid
                    )
            }
            !isPhoneValid(phone) -> {
                _registrationForm.value =
                    RegistrationFormState(
                        phoneError = R.string.login_phone_invalid
                    )
            }
            !isPasswordValid(password) -> {
                _registrationForm.value =
                    RegistrationFormState(
                        passwordError = R.string.login_password_invalid
                    )
            }
            !isConfirmPasswordValid(password, confirmPassword) -> {
                _registrationForm.value =
                    RegistrationFormState(
                        conformPasswordError = R.string.login_confirm_password_invalid
                    )
            }
            else -> {
                _registrationForm.value =
                    RegistrationFormState(
                        isDataValid = true
                    )
            }
        }
    }

    /**
     * Проверяет на правильномть введенного имени пользователя.
     */
    private fun isUserNameValid(userName: String) = userName.isNotEmpty()

    /**
     * Проверяет на правильномть введенного email.
     */
    private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    /**
     * Проверяет на правильность введенного телефона.
     */
    private fun isPhoneValid(phone: String) = Patterns.PHONE.matcher(phone).matches()

    /**
     * Проверяет на правильность введенного пароля.
     */
    private fun isPasswordValid(password: String) = password.length >= 8

    /**
     * Проверяет на правильность подтвержение введенного пароля.
     */
    private fun isConfirmPasswordValid(password: String, confirmPassword: String) =
        password == confirmPassword

    /**
     * ViewModel provider factory to instantiate LoginViewModel.
     * Required given LoginViewModel has a non-empty constructor
     */
    class Factory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(
                    LoginRepository()
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
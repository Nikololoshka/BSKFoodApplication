package com.vereshchagin.nikolay.pepegafood.profile.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionManager
import androidx.transition.Visibility.MODE_IN
import com.vereshchagin.nikolay.pepegafood.R
import com.vereshchagin.nikolay.pepegafood.databinding.ActivityLoginBinding
import com.vereshchagin.nikolay.pepegafood.utils.CommonUtils

/**
 * Активность для входа в приложение.
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginActivityLog"
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // переключение вкладок
        binding.tabs.addOnButtonCheckedListener { group, _, _ ->
            showLoginTab(group.checkedButtonId == R.id.login_tab)
        }

        viewModel = ViewModelProvider(this, LoginViewModel.Factory())
            .get(LoginViewModel::class.java)


        initLoginTab()
        initRegistrationTab()
    }

    /**
     * Инициализация вкладки входа.
     */
    private fun initLoginTab() {
        // проверка формы для входа в систему
        viewModel.loginFormState.observe(this, Observer {
            val loginState = it ?: return@Observer

            binding.loginButton.isEnabled = loginState.isDataValid

            binding.loginEmailLayout.error = if (loginState.emailError != null)
                getString(loginState.emailError) else null

            binding.loginPasswordLayout.error = if (loginState.passwordError != null)
                getString(loginState.passwordError) else null
        })

        binding.loginEmail.doAfterTextChanged {
            updateLoginForm()
        }

        binding.loginPassword.doAfterTextChanged {
            updateLoginForm()
        }

        binding.loginButton.setOnClickListener {
            updateLoginForm()
            if (viewModel.loginFormState.value?.isDataValid!!) {
                return@setOnClickListener
            }

            viewModel.login(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            )
        }
    }

    /**
     * Инициализация вкладки регистрации.
     */
    private fun initRegistrationTab() {
        viewModel.registrationFormState.observe(this, Observer {
            val registrationState = it ?: return@Observer

            binding.registrationButton.isEnabled = registrationState.isDataValid

            binding.registrationUserNameLayout.error = if (registrationState.userNameError != null)
                getString(registrationState.userNameError) else null

            binding.registrationEmailLayout.error = if (registrationState.emailError != null)
                getString(registrationState.emailError) else null

            binding.registrationPhoneLayout.error = if (registrationState.phoneError != null)
                getString(registrationState.phoneError) else null

            binding.registrationPasswordLayout.error = if (registrationState.passwordError != null)
                getString(registrationState.passwordError) else null

            binding.registrationConfirmPasswordLayout.error = if (registrationState.conformPasswordError != null)
                getString(registrationState.conformPasswordError) else null
        })

        binding.registrationUserName.doAfterTextChanged {
            updateRegistrationForm()
        }

        binding.registrationEmail.doAfterTextChanged {
            updateRegistrationForm()
        }

        binding.registrationPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.registrationPhone.doAfterTextChanged {
            updateRegistrationForm()
        }

        binding.registrationPassword.doAfterTextChanged {
            updateRegistrationForm()
        }

        binding.registrationConfirmPassword.doAfterTextChanged {
            updateRegistrationForm()
        }
    }

    /**
     * Обновляет форму входа.
     */
    private fun updateLoginForm() {
        viewModel.loginDataChanged(
            binding.loginEmail.text.toString(),
            binding.loginPassword.text.toString()
        )
    }

    /**
     * Обновляет форму регистрации.
     */
    private fun updateRegistrationForm() {
        viewModel.registrationDataChanged(
            binding.registrationUserName.text.toString(),
            binding.registrationEmail.text.toString(),
            binding.registrationPhone.text.toString(),
            binding.registrationPassword.text.toString(),
            binding.registrationConfirmPassword.text.toString()
        )
    }

    /**
     * Переключает вкладки: вход / регистрация.
     */
    private fun showLoginTab(show: Boolean) {
        val fade = Fade()
        fade.duration = CommonUtils.FADE_DURATION
        TransitionManager.beginDelayedTransition(binding.loginLayout, fade)

        binding.loginContent.visibility = CommonUtils.toVisibly(show)
        binding.registrationContent.visibility = CommonUtils.toVisibly(!show)
    }
}

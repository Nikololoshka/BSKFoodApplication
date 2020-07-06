package com.vereshchagin.nikolay.pepegafood.profile.login

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Fade
import androidx.transition.TransitionManager
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


        binding.phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        viewModel = ViewModelProvider(this, LoginViewModel.Factory())
            .get(LoginViewModel::class.java)

        // проверка формы для входа в систему
        viewModel.loginFormState.observe(this, Observer {
            val loginState = it ?: return@Observer

            binding.loginButton.isEnabled = loginState.isDataValid

            binding.loginEmailLayout.error = if (loginState.emailError != null)
                getString(loginState.emailError) else null

            binding.loginPasswordLayout.error = if (loginState.passwordError != null)
                getString(loginState.passwordError) else null
        })

        initLoginTab()
/*

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
        */
    }

    private fun initLoginTab() {
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

    private fun updateLoginForm() {
        viewModel.loginDataChanged(
            binding.loginEmail.text.toString(),
            binding.loginPassword.text.toString()
        )
    }

    private fun showLoginTab(show: Boolean) {
        val fade = Fade()
        fade.duration = CommonUtils.FADE_DURATION
        TransitionManager.beginDelayedTransition(binding.loginLayout, fade)

        binding.loginContent.visibility = CommonUtils.toVisibly(show)
        binding.registrationContent.visibility = CommonUtils.toVisibly(!show)
    }
}

package com.example.pokeapp.features.auth.presentation.screen.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.usecases.AuthLogin
import com.example.pokeapp.features.auth.domain.usecases.LoginParam
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authLogin: AuthLogin,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update {
            it.copy(username = username)
        }
    }
    fun onPasswordChange(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }

    fun login() {
        val username = _uiState.value.username
        val password = _uiState.value.password

        if (username.isBlank() || password.isBlank()) {
            _uiState.update {
                it.copy(error = context.getString(R.string.username_and_password_cannot_be_empty))
            }
            return
        }

        val param = LoginParam(
            username = username,
            password = password
        )

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val userLogin = authLogin(param)

                if (userLogin != null) {
                    authLogin.saveLoginInfo(userLogin.username)
                    _uiState.update {
                        it.copy(isValid = true)
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isValid = false,
                            error = context.getString(R.string.invalid_username_or_password)
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message ?: context.getString(R.string.error_unknown))
                }
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}
package com.example.pokeapp.features.auth.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.domain.usecases.PostLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLogin: PostLogin
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

    fun clearForm() {
        _uiState.update {
            it.copy(username = "", password = "", error = null)
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    fun login() {
        val username = _uiState.value.username
        val password = _uiState.value.password

        if (username.isBlank()) {
            _uiState.update {
                it.copy(error = LoginState.Error(R.string.username_cannot_be_empty))
            }
            return
        } else if (password.isBlank()) {
            _uiState.update {
                it.copy(error = LoginState.Error(R.string.password_cannot_be_empty))
            }
            return
        }

        val param = PostLogin.Param(
            username = username,
            password = password
        )

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val userLogin = postLogin(param)

                if (userLogin != null) {
                    _uiState.update {
                        it.copy(isValid = true)
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isValid = false,
                            error = LoginState.Error(string = R.string.invalid_username_or_password)
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = LoginState.Error(message = e.message))
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
package com.example.pokeapp.features.auth.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.domain.usecases.PostRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postRegister: PostRegister
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update {
            uiState.value.copy(username = username)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update {
            uiState.value.copy(password = password)
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    fun register() {
        val username = _uiState.value.username
        val password = _uiState.value.password

        if (username.isBlank()) {
            _uiState.update {
                it.copy(error = RegisterState.Error(string = R.string.username_cannot_be_empty))
            }
            return
        } else if (password.isBlank()) {
            _uiState.update {
                it.copy(error = RegisterState.Error(string = R.string.password_cannot_be_empty))
            }
            return
        }

        val param = PostRegister.Param(
            username = username,
            password = password
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                postRegister(param)

                _uiState.update {
                    it.copy(isSuccess = true)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = RegisterState.Error(message = e.message))
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
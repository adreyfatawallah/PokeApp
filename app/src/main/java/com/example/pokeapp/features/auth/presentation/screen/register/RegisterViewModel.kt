package com.example.pokeapp.features.auth.presentation.screen.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.UserRepository
import com.example.pokeapp.features.auth.domain.usecases.AuthRegister
import com.example.pokeapp.features.auth.domain.usecases.RegisterParam
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRegister: AuthRegister,
    @param:ApplicationContext private val context: Context
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

        if (username.isBlank() || password.isBlank()) {
            _uiState.update {
                it.copy(error = context.getString(R.string.username_and_password_cannot_be_empty))
            }
            return
        }

        val param = RegisterParam(
            username = username,
            password = password
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                authRegister(param)

                _uiState.update {
                    it.copy(isSucess = true)
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
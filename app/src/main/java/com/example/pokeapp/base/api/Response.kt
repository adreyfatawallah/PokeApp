package com.example.pokeapp.base.api

//sealed interface Response<out S, out F> {
//    data class Success<out S>(val data: S) : Response<S, Nothing>
//    data class Failure<out F>(val error: F) : Response<Nothing, F>
//}

sealed interface Response<out D, out E: ApiError> {
    data class Success<out D>(val data: D) : Response<D, Nothing>
    data class Failure<out E: ApiError>(val error: E) : Response<Nothing, E>
}

inline fun <T, E: ApiError> Response<T, E>.onSuccess(action: (T) -> Unit) : Response<T, E> {
    return when(this) {
        is Response.Failure -> this
        is Response.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E: ApiError> Response<T, E>.onFailure(action: (E) -> Unit) : Response<T, E> {
    return when(this) {
        is Response.Failure -> {
            action(error)
            this
        }
        is Response.Success -> this
    }
}

inline fun <T, E: ApiError, R> Response<T, E>.map(map: (T) -> R) : Response<R, E> {
    return when(this) {
        is Response.Failure -> Response.Failure(error)
        is Response.Success -> Response.Success(map(data))
    }
}
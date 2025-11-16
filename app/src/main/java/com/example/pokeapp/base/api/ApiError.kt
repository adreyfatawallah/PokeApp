package com.example.pokeapp.base.api

enum class ResponseError {
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    TOO_MANY_REQUESTS,
    INTERNAL_SERVER_ERROR,
    BAD_GATEWAY,
    SERVICE_UNAVAILABLE,
    GATEWAY_TIMEOUT,
    UNKNOWN
}

sealed interface ApiError {
    data class Response(val message: String?) : ApiError
    data class JsonParse(val message: String?) : ApiError
    data class ErrorReponse(val error: ResponseError) : ApiError
    data class Network(val message: String?): ApiError
    data class Unknown(val message: String?) : ApiError
}
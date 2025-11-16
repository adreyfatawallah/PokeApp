package com.example.pokeapp.base.api

import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.io.IOException

inline fun <reified T> safeCall(
    execute: () -> T
) : Response<T, ApiError> {
    try {
        return Response.Success(execute())
    } catch (e: JsonDataException) {
        return Response.Failure(ApiError.JsonParse(e.message))
    } catch (e: HttpException) {
        return when (e.code()) {
            400 -> Response.Failure(ApiError.ErrorReponse(ResponseError.BAD_REQUEST))
            401 -> Response.Failure(ApiError.ErrorReponse(ResponseError.UNAUTHORIZED))
            403 -> Response.Failure(ApiError.ErrorReponse(ResponseError.FORBIDDEN))
            404 -> Response.Failure(ApiError.ErrorReponse(ResponseError.NOT_FOUND))
            429 -> Response.Failure(ApiError.ErrorReponse(ResponseError.TOO_MANY_REQUESTS))
            500 -> Response.Failure(ApiError.ErrorReponse(ResponseError.INTERNAL_SERVER_ERROR))
            502 -> Response.Failure(ApiError.ErrorReponse(ResponseError.BAD_GATEWAY))
            503 -> Response.Failure(ApiError.ErrorReponse(ResponseError.SERVICE_UNAVAILABLE))
            504 -> Response.Failure(ApiError.ErrorReponse(ResponseError.GATEWAY_TIMEOUT))
            else -> Response.Failure(ApiError.ErrorReponse(ResponseError.UNKNOWN))
        }
    } catch (e: IOException) {
        return Response.Failure(ApiError.Network(e.message))
    } catch (e: Exception) {
        return Response.Failure(ApiError.Unknown(e.message))
    }
}
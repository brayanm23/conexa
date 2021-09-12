package com.conexa.networking.status

import com.conexa.networking.status.Status.SERVER_ERROR
import com.conexa.networking.status.Status.SUCCESS

data class Result<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): Result<T> =
            Result(SUCCESS, data)
        fun <T> serverError(data: T? = null): Result<T> =
            Result(SERVER_ERROR, data)
    }
}

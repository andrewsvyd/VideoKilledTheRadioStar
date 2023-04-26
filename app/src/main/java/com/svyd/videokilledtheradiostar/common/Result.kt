package com.svyd.videokilledtheradiostar.common

sealed class Result<out D> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
}

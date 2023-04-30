package com.svyd.videokilledtheradiostar.common

sealed class UiState<out D> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
}

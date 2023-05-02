package com.svyd.videokilledtheradiostar.common

sealed class UiState<out D> (val contentState: ContentState) {
    object Loading : UiState<Nothing>(ContentState.Loading)
    data class Success<out T>(val data: T) : UiState<T>(ContentState.Success)
    data class Error(val error: String) : UiState<Nothing>(ContentState.Error)
}

/**
 *todo: find a proper solution
 * a (very questionable) workaround to avoid triggering CrossFade whenever data changes
 */
sealed class ContentState {
    object Loading : ContentState()
    object Success : ContentState()
    object Error : ContentState()
}

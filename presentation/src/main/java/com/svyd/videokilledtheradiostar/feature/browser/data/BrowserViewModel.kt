package com.svyd.videokilledtheradiostar.feature.browser.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.functional.Either
import com.svyd.domain.common.interactor.Interactor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.model.Directory
import com.svyd.videokilledtheradiostar.common.UiState
import com.svyd.videokilledtheradiostar.feature.browser.model.UiDirectory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @param url a nullable string with destination url.
 *            In case if value is null - the destination is root.
 *            It is the only place where explicit nullable url param is accepted.
 */

class BrowserViewModel(
    url: String?,
    private val directoryInteractor: ParametrizedInteractor<Directory, String>,
    private val rootDirectoryInteractor: Interactor<Directory>,
    private val mapper: TypeMapper<Directory, UiDirectory>
) : ViewModel() {

    private val _directoryState = MutableStateFlow<UiState<UiDirectory>>(UiState.Loading)
    val directoryState: StateFlow<UiState<UiDirectory>> = _directoryState

    private var lastDirectoryRequest: String? = null

    fun retry() {
        directory(lastDirectoryRequest)
    }

    init {
        directory(url)
    }

    private fun directory(url: String?) {
        lastDirectoryRequest = url
        _directoryState.value = UiState.Loading

        val onResult = { result: Either<Failure, UiDirectory> ->
            result.fold(::handleFailure, ::handleDirectory)
        }

        if (url != null) directoryInteractor(url, viewModelScope, mapper, onResult)
        else rootDirectoryInteractor(viewModelScope, mapper, onResult)
    }

    private fun handleDirectory(directory: UiDirectory) {
        _directoryState.value = UiState.Success(directory)
    }

    /**
     * Would be better to delegate error handling to external component
     * to be able to test it in isolation and have different implementations of such component
     * for debug and production flavors:
     * makes sense to rethrow unexpected errors in debug build,
     * but not so much in a production one
     */

    private fun handleFailure(failure: Failure) {
        val message = when (failure) {
            is Failure.NetworkError -> failure.exception.message ?: "Network failure"
            //unrecoverable exception, must be programming error, throwing it here
            is Failure.UnexpectedError -> throw failure.exception
        }
        _directoryState.value = UiState.Error(message)
    }
}

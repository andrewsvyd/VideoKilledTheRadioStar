package com.svyd.domain.common.exception

sealed class Failure(val exception: Throwable) {
    class NetworkError(exception: Throwable) : Failure(exception)
    class UnexpectedError(exception: Throwable) : Failure(exception)
}

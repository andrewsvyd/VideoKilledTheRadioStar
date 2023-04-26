package com.svyd.domain.common.exception

sealed class Failure {
    object NetworkError : Failure()
    object UnexpectedError : Failure()
}

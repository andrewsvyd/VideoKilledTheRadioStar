package com.svyd.domain.common.exception

import com.svyd.domain.common.mapper.TypeMapper

class ErrorMapper : TypeMapper<Throwable, Failure> {
    override fun map(input: Throwable): Failure {
        return Failure.NetworkError(input)
    }
}
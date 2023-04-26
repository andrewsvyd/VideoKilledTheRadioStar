package com.svyd.domain.common

import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.functional.Either
import com.svyd.domain.common.functional.Either.Left
import com.svyd.domain.common.functional.Either.Right
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class Interactor<out Type, in Params>(
    private val errorMapper: TypeMapper<Throwable, Failure>
) {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch {
            val deferred = async { run(params) }
            val result = try {
                Right(deferred.await())
            } catch (error: Throwable) {
                Left(errorMapper.map(error))
            }

            onResult(result)
        }
    }
}

package com.svyd.domain.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class Interactor<out Type, in Params> {

    abstract suspend fun run(params: Params): Result<Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Result<Type>) -> Unit = {}
    ) {
        scope.launch {
            val deferred = async {
                run(params)
            }
            onResult(deferred.await())
        }
    }
}

package com.svyd.domain.common.interactor

import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.functional.Either
import com.svyd.domain.common.functional.Either.Left
import com.svyd.domain.common.functional.Either.Right
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @param Type        domain data type to be returned
 * @param Params      type of input parameters for the use case
 * @param errorMapper used to map java Throwable to our Failure class
 */
abstract class ParametrizedInteractor<out Type, in Params>(
    private val errorMapper: TypeMapper<Throwable, Failure>
) {

    abstract suspend fun run(params: Params): Flow<Type>

    /**
     * @param TargetType presentation layer data model type
     * @param params     for the request
     * @param mapper     to map data model from domain Type to presentation layer TargetType type
     *                   it is important to move all data transformation to the background
     *                   execution without exposing data types from other modules
     */
    operator fun <TargetType> invoke(
        params: Params,
        scope: CoroutineScope,
        mapper: TypeMapper<Type, TargetType>,
        onResult: (Either<Failure, TargetType>) -> Unit = {}
    ) {
        scope.launch {
            run(params)
                .map { mapper.map(it) }
                .catch { onResult(Left(errorMapper.map(it))) }
                .collect { onResult(Right(it)) }
        }
    }
}

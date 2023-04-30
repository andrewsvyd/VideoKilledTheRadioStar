package com.svyd.domain.common.interactor

import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.functional.Either
import com.svyd.domain.common.mapper.TypeMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @param Type        domain data type to be returned
 * @param errorMapper used to map java Throwable to our Failure class
 */
abstract class Interactor<out Type>(
    private val errorMapper: TypeMapper<Throwable, Failure>
) {

    abstract suspend fun run(): Flow<Type>

    /**
     * @param TargetType presentation layer data model type
     * @param mapper     to map data model from domain Type to presentation layer TargetType type
     *                   it is important to move all data transformation to the background
     *                   execution without exposing data types from other modules
     */
    operator fun <TargetType> invoke(
        scope: CoroutineScope,
        mapper: TypeMapper<Type, TargetType>,
        onResult: (Either<Failure, TargetType>) -> Unit = {}
    ) {
        scope.launch {
            run()
                .map { mapper.map(it) }
                .catch { onResult(Either.Left(errorMapper.map(it))) }
                .collect { onResult(Either.Right(it)) }
        }
    }
}

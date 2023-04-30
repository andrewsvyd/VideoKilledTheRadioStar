package com.svyd.domain.interactor

import com.svyd.domain.common.interactor.ParametrizedInteractor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.repository.model.Directory
import com.svyd.domain.repository.DirectoryRepository
import kotlinx.coroutines.flow.Flow

class DirectoryInteractor(
    private val repository: DirectoryRepository,
    errorMapper: TypeMapper<Throwable, Failure>
) : ParametrizedInteractor<Directory, String>(errorMapper) {

    override suspend fun run(params: String): Flow<Directory> = repository.directory(params)

}

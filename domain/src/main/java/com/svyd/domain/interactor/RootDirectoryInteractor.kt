package com.svyd.domain.interactor

import com.svyd.domain.common.exception.Failure
import com.svyd.domain.common.interactor.Interactor
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.DirectoryRepository
import com.svyd.domain.repository.model.Directory
import kotlinx.coroutines.flow.Flow

class RootDirectoryInteractor(
    private val repository: DirectoryRepository,
    errorMapper: TypeMapper<Throwable, Failure>
) : Interactor<Directory>(errorMapper) {

    override suspend fun run(): Flow<Directory> = repository.rootDirectory()
}
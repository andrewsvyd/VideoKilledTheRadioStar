package com.svyd.domain.interactor

import com.svyd.domain.common.Interactor
import com.svyd.domain.common.TypeMapper
import com.svyd.domain.common.exception.Failure
import com.svyd.domain.repository.Directory
import com.svyd.domain.repository.DirectoryRepository

class DirectoryInteractor(
    private val repository: DirectoryRepository,
    errorMapper: TypeMapper<Throwable, Failure>
) : Interactor<Directory, String>(errorMapper) {

    override suspend fun run(params: String): Directory {
        return if (params.isEmpty())
            repository.rootDirectory() else
            repository.directory(params)
    }
}

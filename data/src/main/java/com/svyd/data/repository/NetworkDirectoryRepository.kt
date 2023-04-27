package com.svyd.data.repository

import com.svyd.data.repository.model.DirectoryEntity
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.DirectoryRepository
import com.svyd.domain.repository.model.Directory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkDirectoryRepository(
    private val service: DirectoryService,
    private val mapper: TypeMapper<DirectoryEntity, Directory>
) : DirectoryRepository {

    override fun directory(url: String): Flow<Directory> {
        return flow { emit(mapper.map(service.directory(url))) }
    }

    override fun rootDirectory(): Flow<Directory> {
        return flow { emit(mapper.map(service.directory(ApiConstants.Paths.ROOT_ENDPOINT))) }
    }
}

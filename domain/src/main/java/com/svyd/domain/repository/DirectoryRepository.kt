package com.svyd.domain.repository

import com.svyd.domain.repository.model.Directory
import kotlinx.coroutines.flow.Flow

interface DirectoryRepository {
    fun directory(url: String) : Flow<Directory>
    fun rootDirectory() : Flow<Directory>
}

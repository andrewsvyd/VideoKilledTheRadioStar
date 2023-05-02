package com.svyd.domain.repository

import kotlinx.coroutines.flow.Flow

interface AudioRepository {
    fun audio(url: String) : Flow<List<String>>
}

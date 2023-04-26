package com.svyd.domain.repository

interface DirectoryRepository {
    fun directory(url: String) : Directory
    fun rootDirectory() : Directory
}

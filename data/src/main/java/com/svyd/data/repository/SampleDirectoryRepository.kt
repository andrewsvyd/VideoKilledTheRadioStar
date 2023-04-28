package com.svyd.data.repository

import android.app.Activity
import com.google.gson.Gson
import com.svyd.data.repository.model.DirectoryEntity
import com.svyd.domain.common.mapper.TypeMapper
import com.svyd.domain.repository.model.Directory
import com.svyd.domain.repository.DirectoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException

class SampleDirectoryRepository(
    private val activity: Activity,
    private val mapper: TypeMapper<DirectoryEntity, Directory>
) : DirectoryRepository {

    override fun directory(url: String): Flow<Directory> {
        return flowOf(mapper.map(getSampleDirectory("root/music/top/top.json")))
    }

    override fun rootDirectory(): Flow<Directory> {
        return flowOf(mapper.map(getSampleDirectory("root/podcasts/music/top_40_and_pop/top_40_and_pop.json")))
    }

    private fun getSampleDirectory(path: String): DirectoryEntity {
        return Gson().fromJson(loadJSONFromAsset(path), DirectoryEntity::class.java)
    }

    private fun loadJSONFromAsset(filePath: String): String? {
        val json: String?
        try {
            val inputStream = activity.assets.open(filePath)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}

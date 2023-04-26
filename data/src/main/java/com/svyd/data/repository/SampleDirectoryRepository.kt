package com.svyd.data.repository

import android.app.Activity
import com.google.gson.Gson
import com.svyd.domain.repository.Directory
import com.svyd.domain.repository.DirectoryRepository
import java.io.IOException

class SampleDirectoryRepository(private val activity: Activity) : DirectoryRepository {

    override fun directory(url: String): Directory {
        return getSampleDirectory("root/local_radio/local_radio.json")
    }

    override fun rootDirectory(): Directory {
        return getSampleDirectory("root/browse.json")
    }

    private fun getSampleDirectory(path: String) : Directory {
        return Gson().fromJson(loadJSONFromAsset(path), Directory::class.java)
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

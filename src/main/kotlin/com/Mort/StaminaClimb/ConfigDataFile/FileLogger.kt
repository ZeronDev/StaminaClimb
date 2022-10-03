package com.Mort.StaminaClimb.ConfigDataFile

import kotlinx.coroutines.*
import java.io.File
import java.io.FileWriter

object FileLogger {
    suspend fun write(context: String, loc: File) {
        withContext(Dispatchers.IO) {
            val fileWriter = FileWriter(loc)
            fileWriter.write(context)
            fileWriter.close()
        }
    }
}
package com.example.voca.core.media.recorder

import java.io.File

interface AudioRecorder {

    fun start(outputFile: File)
    fun stop()
}
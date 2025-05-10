package com.example.voca.core.media.player

import java.io.File

interface AudioPlayer {

    fun playFile(file: File)
    fun stop()

}
package com.example.voca.feature.auth.helper

import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Locale

fun parseIsoTimeToMillis(iso: String?): Long? {
    return try {
        iso?.let {
            val input = it.replace("Z", "+0000")
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.getDefault())
            sdf.parse(input)?.time
        }
    } catch (e: Exception) {
        null
    }
}


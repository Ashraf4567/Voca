package com.example.voca.feature.auth.data

import android.os.Build
import com.example.voca.feature.auth.data.model.User
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.time.Instant

class UserMapper {

    fun mapToUser(userInfo: UserInfo): User {
        return User(
            id = userInfo.id,
            email = userInfo.email,
            fullName = extractFullName(userInfo.userMetadata?: JsonObject(emptyMap())),
            avatarUrl = extractAvatarUrl(userInfo.userMetadata?: JsonObject(emptyMap())),
            createdAt = parseTimestampToEpochMillis(userInfo.createdAt.toString()),
            updatedAt = parseTimestampToEpochMillis(userInfo.updatedAt.toString()),
            lastLoginAt = userInfo.lastSignInAt?.let { parseTimestampToEpochMillis(it.toString()) },
            isActive = determineActiveStatus(userInfo)
        )
    }

    private fun extractFullName(userMetadata: JsonObject): String {
        return userMetadata["full_name"]?.jsonPrimitive?.content
            ?: userMetadata["name"]?.jsonPrimitive?.content
            ?: ""
    }

    private fun extractAvatarUrl(userMetadata: JsonObject): String {
        return userMetadata["avatar_url"]?.jsonPrimitive?.content
            ?: userMetadata["picture"]?.jsonPrimitive?.content
            ?: ""
    }

    private fun parseTimestampToEpochMillis(timestamp: String): Long {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Instant.parse(timestamp).toEpochMilli()
            } else {
                0L
            }
        } catch (e: Exception) {
            0L
        }
    }

    private fun determineActiveStatus(userInfo: UserInfo): Boolean {
        // User is considered active if they have a confirmed email and recent sign in
        return userInfo.emailConfirmedAt != null &&
                userInfo.lastSignInAt != null &&
                userInfo.role == "authenticated"
    }
}

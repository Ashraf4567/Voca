package com.example.voca.feature.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: String,
    val email: String?,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("created_at")
    val createdAt: Long,
    @SerialName("updated_at")
    val updatedAt: Long?,
    @SerialName("last_login_at")
    val lastLoginAt: Long?,
    @SerialName("is_active")
    val isActive: Boolean
)
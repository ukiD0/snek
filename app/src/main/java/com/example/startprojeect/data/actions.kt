package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class actions(
    var id: String? = null,
    var created_at: String? = null,
    var photo: String? = null
)

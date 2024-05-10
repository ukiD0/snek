package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class category(
    var id: String? = null,
    var name: String? = null,
    var created_at: String? = null
    )

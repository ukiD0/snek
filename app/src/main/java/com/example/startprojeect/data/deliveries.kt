package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class deliveries (
    var id: String? = null,
    var created_at: String? = null,
    var city: String? = null,
    var coast: Int? = null
)
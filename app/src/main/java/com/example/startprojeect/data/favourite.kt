package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class favourite(
    var id: String? = null,
    var product_id: String? = null,
    var user_id: String? = null
)
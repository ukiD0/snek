package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class cart(
    var id: String? = null,
    var product_id: String? = null,
    var user_id: String? = null,
    var count: Int? = null,
)

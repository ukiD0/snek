package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class favorite_product(
    var id: String? = null,
    var user_id: String? = null,
    var product_id: String? = null,
    var created_at: String? = null
)
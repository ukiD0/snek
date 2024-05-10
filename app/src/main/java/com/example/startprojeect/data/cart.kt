package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class cart(
    var id: Int? = null,
    var order_id: String? = null,
    var product_id: String? = null,
    var count: String? = null,
    var created_at: String? = null,
    var user_id: String? = null
)

package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class order_items(
    var id: String? = null,
    var created_at: String? = null,
    var title: String? = null,
    var coast: Float? = null,
    var count: Int? = null,
    var order_id: Int? = null,
    var product_id: String? = null,
)

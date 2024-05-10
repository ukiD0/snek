package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class orders(
    var id: String? = null,
    var user_id: String? = null,
    var code: Int? = null,
    var delivery_price: Float? = null,
    var subtotal: Float? = null,
    var total_price: Float?  = null,
    var created_at: String? = null,
    var status_id: String? = null,
    var package_items: String? = null,
    var payed: Boolean? = null,
    var order_details_id: String? = null
)

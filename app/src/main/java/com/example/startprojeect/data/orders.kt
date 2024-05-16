package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class orders(
    var id: String? = null,
    var created_at: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var user_id: String? = null,
    var payment_id: String? = null,
    var delivery_coast: Int? = null,
    var status_id: String? = null
)

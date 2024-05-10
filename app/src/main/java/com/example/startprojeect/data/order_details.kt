package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class order_details(
    var  id: String? = null,
    var user_id: String? = null,
    var contact_email: String? = null,
    var contact_phone: String? = null,
    var address: String? = null,
    var payment_method:String? = null,
    var created_at: String? = null,
    var longitude: String? = null,
    var latitude: String? = null
)

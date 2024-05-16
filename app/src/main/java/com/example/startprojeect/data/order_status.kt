package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class order_status(
    var id: String? = null,
    var created_at: String? = null,
    var name: String? = null,

)

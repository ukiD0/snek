package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class payments(
    var id: String? = null,
    var created_at: String? = null,
    var user_id: String? =null,
    var card_name: String? = null,
    var card_number: Int? = null,

)

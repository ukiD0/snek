package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class profile(
    var id: String? =null,
    var created_at: String? = null,
    var user_id: String?  = null,
    var photo: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var address: String? =null,
    var phone: String? = null
)

package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class profile(
    var id: String? =null,
    var user_id: String?  = null,
    var name: String? = null,
    var surname: String? = null,
    var avatar: String? = null,
    var balance: Float? = null,
    var phone: String? = null,
    var location: String? = null,
    var created_at: String? = null
)

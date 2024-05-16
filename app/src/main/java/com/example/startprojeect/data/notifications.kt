package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class notifications(
    var id: String? = null,
    var created_at: String? = null,
    var text: String? = null,
    var title: String? = null,
    var user_id: String? = null,
    var is_read: Boolean? = null
)

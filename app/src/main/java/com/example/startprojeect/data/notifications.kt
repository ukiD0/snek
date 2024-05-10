package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class notifications(
    var id: Int? = null,
    var user_id: String? = null,
    var title: String? = null,
    var text: String? = null,
    var created_at: String? = null
)

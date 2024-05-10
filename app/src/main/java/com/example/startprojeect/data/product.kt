package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class product(
    var id: String? = null,
    var category_id: String? = null,
    var name: String? = null,
    var price: Float? = null,
    var description: String? = null,
    var image: String? = null,
    var created_at: String? = null
)

package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class product_images (
    var id: String? = null,
    var product_id: String? = null,
    var image_url: String? = null,
    var created_at: String? = null
)
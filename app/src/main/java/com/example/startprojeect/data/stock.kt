package com.example.startprojeect.data

import kotlinx.serialization.Serializable

@Serializable
data class stock(
    var id: String? = null,
    var stock_img_url: String? = null,
    var discount: Int? = null,
    var created_at: String? = null
)
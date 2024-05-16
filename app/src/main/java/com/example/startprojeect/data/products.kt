package com.example.startprojeect.data

import android.provider.ContactsContract.DisplayPhoto
import kotlinx.serialization.Serializable

@Serializable
data class products(
    var id: String? = null,
    var title: String? = null,
    var category_id: String? = null,
    var cost: Float? = null,
    var description: String? = null,
    var is_best_seller: Boolean? = null,
    var photo: String? = null
)

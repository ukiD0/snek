package com.example.startprojeect.data.repository

import com.example.startprojeect.data.category

class CategoryLocal: CategoryInterface {
    override suspend fun getCategoryes(): List<category> {
        return arrayListOf(
            category("123","mewwww","12.32.1223"),
            category("123","mewwww","12.32.1223"),
            category("123","mewwww","12.32.1223"),
            )
    }
}
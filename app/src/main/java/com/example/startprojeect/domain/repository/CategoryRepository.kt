package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.categories

class CategoryRepository(private val categoryInterface: CategoryInterface) {
    suspend fun getCategoryes(): List<categories>? {
        return categoryInterface.getCategoryes()
    }
}
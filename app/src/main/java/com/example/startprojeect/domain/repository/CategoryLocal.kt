package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.categories
import io.paperdb.Paper

class CategoryLocal: CategoryInterface {
    override suspend fun getCategoryes(): List<categories>? {
        return Paper.book().read<List<categories>>("categories")
    }
}
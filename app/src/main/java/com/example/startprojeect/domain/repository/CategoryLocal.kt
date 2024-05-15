package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.category
import io.paperdb.Paper

class CategoryLocal: CategoryInterface {
    override suspend fun getCategoryes(): List<category>? {
        return Paper.book().read<List<category>>("categories")
    }
}
package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import com.example.startprojeect.data.repository.CategoryInterface
import com.example.startprojeect.data.repository.CategoryLocal
import com.example.startprojeect.data.repository.CategoryModel
import com.example.startprojeect.data.repository.CategoryRepository
import io.github.jan.supabase.postgrest.from

class CategoryViewModel:ViewModel() {

    private val repository = CategoryRepository(CategoryLocal())

    suspend fun getCategoryes(): List<category> {
        return repository.getCategoryes()
    }
}
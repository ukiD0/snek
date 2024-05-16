package com.example.startprojeect.domain

import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.categories
import com.example.startprojeect.domain.repository.CategoryModel
import com.example.startprojeect.domain.repository.CategoryRepository
import io.paperdb.Paper

class CategoryViewModel:ViewModel() {

    private val repositoryApi = CategoryRepository(CategoryModel())
    var currentCategoryID = ""
    var currentCategory = ""

    suspend fun getCategoryes(): List<categories> {
        try {
            val categories = repositoryApi.getCategoryes()
            categories
            Paper.book().write("categories", categories!!)
            return categories
        }catch (e:Exception){
            return Paper.book().read<List<categories>>("categories")!!
        }

    }
}
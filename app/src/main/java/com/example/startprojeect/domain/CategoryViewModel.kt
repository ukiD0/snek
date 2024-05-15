package com.example.startprojeect.domain

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.example.startprojeect.data.DbCon
import com.example.startprojeect.data.category
import com.example.startprojeect.domain.repository.CategoryInterface
import com.example.startprojeect.domain.repository.CategoryLocal
import com.example.startprojeect.domain.repository.CategoryModel
import com.example.startprojeect.domain.repository.CategoryRepository
import io.github.jan.supabase.postgrest.from
import io.paperdb.Paper

class CategoryViewModel:ViewModel() {

    private val repositoryApi = CategoryRepository(CategoryModel())

    suspend fun getCategoryes(): List<category> {
        try {
            val categories = repositoryApi.getCategoryes()
            categories
            Paper.book().write("categories", categories!!)
            return categories
        }catch (e:Exception){
            return Paper.book().read<List<category>>("categories")!!
        }

    }
}
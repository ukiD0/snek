package com.example.startprojeect.domain.repository

import com.example.startprojeect.data.categories

interface CategoryInterface {
    suspend fun getCategoryes(): List<categories>?

}
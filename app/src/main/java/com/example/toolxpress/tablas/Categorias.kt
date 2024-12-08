package com.example.toolxpress.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val IdCategoria: Int = 0,
    val nombre: String
)

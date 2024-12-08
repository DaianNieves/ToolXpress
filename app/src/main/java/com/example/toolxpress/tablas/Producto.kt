package com.example.toolxpress.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "productos",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["IdCategoria"],
            childColumns = ["IdCategoria"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Producto(
    @PrimaryKey(autoGenerate = true) val idProducto: Int = 0,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val cantidad: Int,
    val imagen: String?,

    @ColumnInfo(index = true) val IdCategoria: Int
)
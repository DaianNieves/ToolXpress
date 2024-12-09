package com.example.toolxpress.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val idUsuario: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "correo") val correo: String,
    @ColumnInfo(name = "contrasena") val contrasena: String,
    @ColumnInfo(name = "codigoPostal") val codigoPostal: String? = null,
    @ColumnInfo(name = "estado") val estado: String? = null,
    @ColumnInfo(name = "municipio") val municipio: String? = null,
    @ColumnInfo(name = "colonia") val colonia: String? = null,
    @ColumnInfo(name = "calle") val calle: String? = null,
    @ColumnInfo(name = "telefono") val telefono: String? = null,
    @ColumnInfo(name = "numCasa") val numCasa: String? = null

)

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey(autoGenerate = true) val idCategoria: Int = 0,
    @ColumnInfo(name = "nombreCat") val nombreCat: String
)

@Entity(
    tableName = "productos",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["idCategoria"],
            childColumns = ["idCategoria"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Producto(
    @PrimaryKey(autoGenerate = true) val idProducto: Int = 0,
    @ColumnInfo(name = "idCategoria") val idCategoria: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "descripcion") val descripcion: String? = null,
    @ColumnInfo(name = "precio") val precio: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "imagen") val imagen: String
)

@Entity(
    tableName = "carrito",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Producto::class,
            parentColumns = ["idProducto"],
            childColumns = ["idProducto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Carrito(
    @PrimaryKey(autoGenerate = true) val idCarrito: Int = 0,
    @ColumnInfo(name = "idUsuario") val idUsuario: Int,
    @ColumnInfo(name = "idProducto") val idProducto: Int,
    @ColumnInfo(name = "cantidadProducto") val cantidadProducto: Int
)
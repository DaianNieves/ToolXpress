package com.example.toolxpress.tablas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val IdUsuario: Int = 0,
    val nombre: String,
    val correo: String,
    val contraseña: String,
    val codigoPostal: String,
    val estado: String,
    val municipio: String,
    val colonia: String,
    val calle: String,
    val numeroTelefono: String,
    val referencia1: String? = null, // Puede ser nulo
    val referencia2: String? = null, // Puede ser nulo
    val telefono: String
)
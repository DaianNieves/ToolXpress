package com.example.toolxpress.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.toolxpress.tablas.Categoria
import com.example.toolxpress.tablas.Producto
import com.example.toolxpress.tablas.Usuario

@Dao
interface ServiceDao {
    // CRUD para Usuario
    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Update
    suspend fun actualizarUsuario(usuario: Usuario)

    @Delete
    suspend fun eliminarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE IdUsuario = :id")
    suspend fun obtenerUsuarioPorId(id: Int): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun obtenerTodosUsuarios(): List<Usuario>

    // CRUD para Categoria
    @Insert
    suspend fun insertarCategoria(categoria: Categoria)

    @Update
    suspend fun actualizarCategoria(categoria: Categoria)

    @Delete
    suspend fun eliminarCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias WHERE IdCategoria = :id")
    suspend fun obtenerCategoriaPorId(id: Int): Categoria?

    @Query("SELECT * FROM categorias")
    suspend fun obtenerTodasCategorias(): List<Categoria>

    // CRUD para Producto
    @Insert
    suspend fun insertarProducto(producto: Producto)

    @Update
    suspend fun actualizarProducto(producto: Producto)

    @Delete
    suspend fun eliminarProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE idProducto = :id")
    suspend fun obtenerProductoPorId(id: Int): Producto?

    @Query("SELECT * FROM productos")
    suspend fun obtenerTodosProductos(): List<Producto>

    @Query("SELECT * FROM productos WHERE IdCategoria = :idCategoria")
    suspend fun obtenerProductosPorCategoria(idCategoria: Int): List<Producto>
}
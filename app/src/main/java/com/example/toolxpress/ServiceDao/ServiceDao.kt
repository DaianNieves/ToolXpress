package com.example.toolxpress.ServiceDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.toolxpress.data.model.Carrito
import com.example.toolxpress.data.model.Categoria
import com.example.toolxpress.data.model.Producto
import com.example.toolxpress.data.model.Usuario

@Dao
interface ServiceDao {

    // CRUD para la tabla Usuario

    @Insert
    suspend fun insertUsuario(usuario: Usuario)

    @Update
    suspend fun updateUsuario(usuario: Usuario)

    @Delete
    suspend fun deleteUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE idUsuario = :id")
    suspend fun getUsuarioById(id: Int): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<Usuario>

    // CRUD para la tabla Categoria

    @Insert
    suspend fun insertCategoria(categoria: Categoria)

    @Update
    suspend fun updateCategoria(categoria: Categoria)

    @Delete
    suspend fun deleteCategoria(categoria: Categoria)

    @Query("SELECT * FROM categorias WHERE idCategoria = :id")
    suspend fun getCategoriaById(id: Int): Categoria?

    @Query("SELECT * FROM categorias")
    suspend fun getAllCategorias(): List<Categoria>

    // CRUD para la tabla Producto

    @Insert
    suspend fun insertProducto(producto: Producto)

    @Update
    suspend fun updateProducto(producto: Producto)

    @Delete
    suspend fun deleteProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE idProducto = :id")
    suspend fun getProductoById(id: Int): Producto?

    @Query("SELECT * FROM productos WHERE idCategoria = :categoriaId")
    suspend fun getProductosByCategoria(categoriaId: Int): List<Producto>

    @Query("SELECT * FROM productos")
    suspend fun getAllProductos(): List<Producto>

    // CRUD para la tabla Carrito

    @Insert
    suspend fun insertCarrito(carrito: Carrito)

    @Update
    suspend fun updateCarrito(carrito: Carrito)

    @Delete
    suspend fun deleteCarrito(carrito: Carrito)

    @Query("SELECT * FROM carrito WHERE idCarrito = :id")
    suspend fun getCarritoById(id: Int): Carrito?

    @Query("SELECT * FROM carrito WHERE idUsuario = :usuarioId")
    suspend fun getCarritosByUsuario(usuarioId: Int): List<Carrito>

    @Query("SELECT * FROM carrito WHERE idProducto = :productoId")
    suspend fun getCarritosByProducto(productoId: Int): List<Carrito>

    @Query("SELECT * FROM carrito")
    suspend fun getAllCarritos(): List<Carrito>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Si el producto ya está en el carrito, lo actualizamos
    suspend fun insertOrUpdateCarrito(carrito: Carrito)
}
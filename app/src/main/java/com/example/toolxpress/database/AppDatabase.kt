package com.example.toolxpress.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toolxpress.Dao.ServiceDao
import com.example.toolxpress.tablas.Categoria
import com.example.toolxpress.tablas.Producto
import com.example.toolxpress.tablas.Usuario


@Database(entities = [Usuario::class, Categoria::class, Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}
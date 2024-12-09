package com.example.toolxpress.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.toolxpress.ServiceDao.ServiceDao
import com.example.toolxpress.data.model.Categoria
import com.example.toolxpress.data.model.Usuario
import com.example.toolxpress.data.model.Producto
import com.example.toolxpress.data.model.Carrito

@Database(
    entities = [
        Usuario::class,
        Categoria::class,
        Producto::class,
        Carrito::class,
        // otras entidades que puedas tener como Respuesta, Pregunta, etc.
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun serviceDao(): ServiceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
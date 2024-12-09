package com.example.toolxpress.data.model

import androidx.lifecycle.ViewModel
import com.example.toolxpress.R

class ProductViewModel : ViewModel() {
    private val products = listOf(
        Product(1, "Engrapadora", "Engrapadora Tipo Pistola Para Tapiceria Con 3000 Grapas", 188.0, R.drawable.engrapadora),
        Product(2, "Kit desarmador", "Juego P/reparación De Celulares Y Disp. Electrónicos,77 Pzas", 295.0, R.drawable.desarmador),
        Product(3, "Pinza de presión", "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270", 94.0, R.drawable.pinza),
        Product(4, "Martillo Uña Recta", "Martillo Uña Recta, 16oz, Mango Fibra De Vidrio Truper 19997", 149.00, R.drawable.martillo),
        Product(5, "Pinza de presión", "Pinza Presión 10' Mordaza Recta Pretul Granel Pretul 2270", 94.00, R.drawable.pinza),
        Product(6, "Escalera Tubular", "Escalera Tubular, Plegable, 2 Peldaños, Pretul Pretul 24118", 595.00, R.drawable.escaleras),
        Product(7, "Taladro Inalámbrico", "NANWEI Kit de Taladro Inalámbrico Electrico", 594.0, R.drawable.taladro),
        Product(8, "Pulidora inalámbrica", "Esmeriladora Angular Pulidora Inalambrica Con Accesorios", 799.0, R.drawable.pulidora),
        Product(9, "Lijadora", "Lijadora Roto Orbital Profesional Shawty C/16 Lija 14000 Opm", 748.0, R.drawable.lijadora),
        Product(10, "Pistola de calor", "RexQualis de 2000w Temperatura Regulable 4 Boquillas", 384.0, R.drawable.pistolacalor),
        Product(11, "Multímetro Digital", "Multímetro Digital Profesional Xl830l Medidor Corriente Mano", 93.0, R.drawable.multimetro),
        Product(12, "Calibrador digital", "Calibrador Digital RexQualis 6in Precisión 0.01mm Metal", 249.0, R.drawable.calibrador),
        Product(13, "Multímetro de gancho", "AstroAI Multimetro de Gancho, Pinza Amperimétrica", 610.0, R.drawable.calibradorgancho)
    )

    // Función para obtener un producto por su ID
    fun getProductById(productId: Int): Product? {
        return products.find { it.id == productId } // Devuelve null si no se encuentra
    }
}
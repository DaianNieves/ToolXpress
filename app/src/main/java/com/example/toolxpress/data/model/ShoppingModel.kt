package com.example.toolxpress.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: Int // Asumimos que es el recurso ID de la imagen
)

data class ShoppingModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int,
    val price: String,
    var selectedQuantity: Int
)

fun ShoppingModel.toCarritoEntity(userId: Int): Carrito {
    return Carrito(
        idUsuario = userId,
        idProducto = this.id,
        cantidadProducto = this.selectedQuantity
    )
}

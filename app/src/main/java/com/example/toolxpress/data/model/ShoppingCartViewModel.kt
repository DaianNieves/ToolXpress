package com.example.toolxpress.data.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ShoppingCartViewModel : ViewModel() {
    // Lista de productos en el carrito
    private val _shoppingCart = mutableStateListOf<ShoppingModel>()
    val shoppingCart: List<ShoppingModel> get() = _shoppingCart

    // Añadir un producto al carrito (si ya está, aumentar la cantidad)
    fun addProductToCart(product: ShoppingModel) {
        println("Antes de agregar: ${_shoppingCart.size}")  // Imprimir tamaño del carrito
        val existingProduct = _shoppingCart.find { it.id == product.id }
        if (existingProduct != null) {
            // Si el producto ya está en el carrito, actualizamos la cantidad
            val updatedProduct = existingProduct.copy(
                selectedQuantity = existingProduct.selectedQuantity + product.selectedQuantity
            )
            updateProductInCart(updatedProduct)
        } else {
            // Si no está, lo agregamos
            _shoppingCart.add(product)
        }
        println("Después de agregar: ${_shoppingCart.size}")  // Imprimir tamaño después
    }

    // Eliminar un producto del carrito
    fun removeProductFromCart(productId: Int) {
        _shoppingCart.removeIf { it.id == productId }
    }

    // Limpiar el carrito
    fun clearCart() {
        println("Antes de borrar: ${_shoppingCart.size}")
        _shoppingCart.clear()
        println("Después de borrar: ${_shoppingCart.size}")
    }

    // Actualizar la cantidad de un producto existente en el carrito
    fun updateProductQuantity(productId: Int, newQuantity: Int) {
        val productIndex = _shoppingCart.indexOfFirst { it.id == productId }
        if (productIndex != -1 && newQuantity > 0) {
            val product = _shoppingCart[productIndex]
            val updatedProduct = product.copy(selectedQuantity = newQuantity)
            updateProductInCart(updatedProduct)
        }
    }

    // Calcular el total del carrito
    fun calculateTotal(): Double {
        return _shoppingCart.sumOf {
            (it.price.replace("$", "").toDoubleOrNull() ?: 0.0) * it.selectedQuantity
        }
    }

    // Método privado para actualizar un producto en el carrito
    private fun updateProductInCart(updatedProduct: ShoppingModel) {
        val productIndex = _shoppingCart.indexOfFirst { it.id == updatedProduct.id }
        if (productIndex != -1) {
            _shoppingCart[productIndex] = updatedProduct
        }
    }
}


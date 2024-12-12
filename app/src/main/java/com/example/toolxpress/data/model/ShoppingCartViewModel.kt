package com.example.toolxpress.data.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ShoppingCartViewModel : ViewModel() {
    // Lista de productos en el carrito
    private val _shoppingCart = mutableStateListOf<ShoppingModel>()
    val shoppingCart: List<ShoppingModel> get() = _shoppingCart

    // Lista de compras realizadas (historial de compras)
    private val _purchaseHistory = mutableStateListOf<List<ShoppingModel>>()
    val purchaseHistory: List<List<ShoppingModel>> get() = _purchaseHistory

    // Añadir un producto al carrito (si ya está, aumentar la cantidad)
    fun addProductToCart(product: ShoppingModel) {
        val existingProduct = _shoppingCart.find { it.id == product.id }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(
                selectedQuantity = existingProduct.selectedQuantity + product.selectedQuantity
            )
            updateProductInCart(updatedProduct)
        } else {
            _shoppingCart.add(product)
        }
    }

    // Eliminar un producto del carrito
    fun removeProductFromCart(productId: Int) {
        _shoppingCart.removeIf { it.id == productId }
    }

    // Limpiar el carrito
    fun clearCart() {
        _shoppingCart.clear()
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

    // Finalizar compra y guardar en historial
    fun completePurchase() {
        // Guardar los productos del carrito en el historial
        _purchaseHistory.add(_shoppingCart.toList())
        // Limpiar el carrito después de la compra
        clearCart()
    }

    // Método privado para actualizar un producto en el carrito
    private fun updateProductInCart(updatedProduct: ShoppingModel) {
        val productIndex = _shoppingCart.indexOfFirst { it.id == updatedProduct.id }
        if (productIndex != -1) {
            _shoppingCart[productIndex] = updatedProduct
        }
    }
}

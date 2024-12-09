package com.example.toolxpress.data.model

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toolxpress.Database.AppDatabase
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel() {
    // Lista de productos en el carrito
    private val _shoppingCart = mutableStateListOf<ShoppingModel>()
    val shoppingCart: List<ShoppingModel> get() = _shoppingCart

    // Añadir un producto al carrito (si ya está, aumentar la cantidad)
    fun addProductToCart(product: ShoppingModel) {
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
    }

    // Eliminar un producto del carrito
    fun removeProductFromCart(productId: Int) {
        _shoppingCart.removeIf { it.id == productId }
    }

    // Actualizar la cantidad de un producto existente
    fun updateProductQuantity(productId: Int, newQuantity: Int) {
        val productIndex = _shoppingCart.indexOfFirst { it.id == productId }
        if (productIndex != -1) {
            val product = _shoppingCart[productIndex]
            val updatedProduct = product.copy(selectedQuantity = newQuantity)
            updateProductInCart(updatedProduct)
        }
    }

    // Calcular el total del carrito
    fun calculateTotal(): Double {
        return _shoppingCart.sumOf { (it.price.replace("$", "").toDoubleOrNull() ?: 0.0) * it.selectedQuantity }
    }

    // Método privado para actualizar un producto en la lista
    private fun updateProductInCart(updatedProduct: ShoppingModel) {
        val productIndex = _shoppingCart.indexOfFirst { it.id == updatedProduct.id }
        if (productIndex != -1) {
            _shoppingCart[productIndex] = updatedProduct
        }
    }
}

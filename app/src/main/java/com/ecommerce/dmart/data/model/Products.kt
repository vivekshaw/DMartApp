package com.ecommerce.dmart.data.model

data class Products(
    val products: List<Product>
) {
    data class Product(
        val Location: String,
        val id: String,
        val img: String,
        val name: String,
        val price: Int
    )
}
package com.ecommerce.dmart.data.localdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity (
    @PrimaryKey val userId: Int,
    val name: String,
    val age: Int
)
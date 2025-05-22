package com.example.shopflowscreen.data

import androidx.annotation.DrawableRes

data class PromotionData(
    val title: String,
    val description: String,
    @DrawableRes val backgroundImage: Int
)

data class ProductData(
    val name: String,
    val description: String,
    val skinType: String,
    val price: String,
    val originalPrice: String,
    val rating: Float,
    val reviewCount: Int,
    val inStock: Boolean,
    val stockCount: Int,
    val imageRes: Int,
    val isFavorite: Boolean = false
)


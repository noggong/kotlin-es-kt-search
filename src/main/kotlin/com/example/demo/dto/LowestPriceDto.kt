package com.example.demo.dto

data class PriceIdless (
    val category: String,
    val brand: String,
    val price: Int)

data class LowestPriceByCategoryDto(
    val prices: List<PriceIdless>,
    val total: Int
)

data class LowestPriceByBrandDto(
    val brand: String,
    val total: Int,
)

data class PriceByCategoryDto(
    val highest: PriceIdless,
    val lowest: PriceIdless,
)
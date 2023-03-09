package com.example.demo.dto

data class PriceIdless (
    val category: String,
    val brand: String,
    val price: Int){}

data class LowestPriceByCategoryResponseDto(
    val price: List<PriceIdless>,
    val total: Int
)

data class LowestPriceByBrandResponseDto(
    val brand: String,
    val total: Int,
)

data class PriceByCategoryResponseDto(
    val highest: PriceIdless,
    val lowest: PriceIdless,
)
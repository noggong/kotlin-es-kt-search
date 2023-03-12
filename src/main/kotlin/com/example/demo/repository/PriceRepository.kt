package com.example.demo.repository

import com.example.demo.document.*

interface PriceRepository {
    fun lowestPriceByCategory(): LowestPriceTerms
    fun lowestPriceByBrand(): BrandPriceAgg
    fun priceByCategory(category: String): CategoryPriceAgg
    fun save(price: Price): Price
    fun findById(id: String): Price?
    fun delete(id: String): Unit
}
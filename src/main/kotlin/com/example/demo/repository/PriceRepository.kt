package com.example.demo.repository

import com.example.demo.document.Price
import com.example.demo.document.LowestPriceByBrandESDto
import com.example.demo.document.LowestPriceTerms
import com.example.demo.document.PriceByCategoryESDto
import java.util.Optional

interface PriceRepository {
    fun lowestPriceByCategory(): LowestPriceTerms
    fun lowestPriceByBrand(): LowestPriceByBrandESDto
    fun priceByCategory(category: String): PriceByCategoryESDto
    fun save(price: Price): Price
    fun findById(id: String): Price?
    fun delete(id: String): Unit
}
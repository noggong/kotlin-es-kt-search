package com.example.demo.repository

import com.example.demo.document.Price
import com.example.demo.dto.LowestPriceESDto
import com.example.demo.dto.PriceByCategoryESDto
import java.util.Optional

interface PriceRepository {
    fun lowestPriceByCategory(): LowestPriceESDto
    fun lowestPriceByBrand()
    fun priceByCategory(category: String): PriceByCategoryESDto
    fun save(price: Price): Price
    fun findById(id: String): Price?
    fun delete(id: String): Unit
}
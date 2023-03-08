package com.example.demo.service

import com.example.demo.dto.PriceDto
import com.example.demo.entity.Price
import com.example.demo.repository.PriceRepository
import org.springframework.stereotype.Service

@Service
class PriceService (
    val priceRepository: PriceRepository
    ) {

    fun addPrice(priceDto: PriceDto): Price {
        val productEntity = priceDto.let {
            Price(null, priceDto.category, priceDto.price, priceDto.brand)
        }
        priceRepository.save(productEntity)
        return productEntity
    }
}
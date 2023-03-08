package com.example.demo.service

import com.example.demo.dto.ProductDto
import com.example.demo.entity.Product
import com.example.demo.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (
    val productRepository: ProductRepository
    ) {

    fun addPrice(productDto: ProductDto): Product {
        val productEntity = productDto.let {
            Product(null, productDto.category, productDto.price, productDto.brand)
        }
        productRepository.save(productEntity)
        return productEntity
    }
}
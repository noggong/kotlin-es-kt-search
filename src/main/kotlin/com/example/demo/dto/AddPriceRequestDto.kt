package com.example.demo.dto

import javax.validation.constraints.*

data class AddPriceRequestDto(
    @get:NotNull(message = "Price must not be blank")
    @get:Min(0)
    val price: Int,

    @get:NotBlank(message = "Category must not be blank")
    val category: String,

    @get:NotBlank(message = "Brand must not be blank")
    val brand: String
) {
}
package com.example.demo.dto

import jakarta.validation.constraints.*

data class UpdatePriceRequestDto(
    @get:NotNull(message = "Price must not be blank")
    @get:Min(0)
    val price: Int,
) {}

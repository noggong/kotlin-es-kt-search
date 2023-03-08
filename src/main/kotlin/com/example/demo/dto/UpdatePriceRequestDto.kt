package com.example.demo.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class UpdatePriceRequestDto(
    @get:NotNull(message = "Price must not be blank")
    @get:Min(0)
    val price: Int,
) {}

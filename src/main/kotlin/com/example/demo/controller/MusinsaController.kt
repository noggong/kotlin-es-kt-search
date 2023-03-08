package com.example.demo.controller

import com.example.demo.dto.AddPriceRequestDto
import com.example.demo.dto.PriceDto
import com.example.demo.dto.UpdatePriceRequestDto
import com.example.demo.entity.Price
import com.example.demo.service.PriceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping
class MusinsaController(
    val priceService: PriceService
) {

    /**
     * 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할때 최저가 조회 API
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun greeting(): String = "hello world"

    /**
     * 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할때 최저가 조회 API
     */
    @GetMapping("categories/lowest")
    fun lowestPriceOfAllCategory() {
        TODO("not yet")
    }

    /**
     * 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
     */
    fun lowestPriceOfABrand() {
        TODO("not yet")
    }

    /**
     * 브랜드 상품 가격 추가
     */
    @PostMapping("/prices")
    fun addPrice(@RequestBody @Valid addPriceDto: AddPriceRequestDto): ResponseEntity<Price> {
        val priceDto = PriceDto(null, addPriceDto.category, addPriceDto.price, addPriceDto.brand)
        val newProductEntity = priceService.addPrice(priceDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductEntity)
    }



    /**
     * 브랜드 상품 가격 업데이트
     */
    @PatchMapping("/prices/{priceId}")
    fun updatePrice(@RequestBody @Valid updatePriceDto: UpdatePriceRequestDto) {
    }

    /**
     * 브랜드 상품 가격 삭제
     */
    fun deletePrice() {
        TODO("not yet")
    }




}
package com.example.demo.controller

import com.example.demo.dto.*
import com.example.demo.document.Price
import com.example.demo.exception.PriceNotFoundException
import com.example.demo.service.PriceService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class MusinsaController(
    val priceService: PriceService
) {

    /**
     * health check
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun greeting(): String = "hello world"

    /**
     * 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할때 최저가 조회 API
     */
    @GetMapping("/lowest-price/by/category")
    fun lowestPriceByCategory(): ResponseEntity<LowestPriceByCategoryDto> = ResponseEntity
        .ok()
        .body(priceService.lowestPriceByCategory())

    /**
     * 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
     */
    @GetMapping("lowest-price/by/brand")
    fun lowestPriceByBrand(): ResponseEntity<LowestPriceByBrandDto> = ResponseEntity
        .ok()
        .body(priceService.lowestPriceByBrand())

    /**
     * 각 카테고리 이름으로 최소, 최대 가격 조회 API
     */
    @GetMapping("price/by/{category}")
    fun priceByCategory(@PathVariable("category") category: String): ResponseEntity<PriceByCategoryDto> = ResponseEntity
        .ok()
        .body(priceService.priceByCategory(category))

    /**
     * 브랜드 상품 가격 추가
     */
    @PostMapping("/prices")
    fun addPrice(@RequestBody @Valid addPriceDto: AddPriceRequestDto): ResponseEntity<Price> {
        val priceDto = PriceDto(null, addPriceDto.category, addPriceDto.price, addPriceDto.brand)
        val newPriceDocument = priceService.addPrice(priceDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(newPriceDocument)
    }

    /**
     * 브랜드 상품 가격 업데이트
     */
    @PatchMapping("/prices/{priceId}")
    fun updatePrice(@PathVariable("priceId") priceId: String,
        @RequestBody @Valid updatePriceDto: UpdatePriceRequestDto): ResponseEntity<Price> {
        val existingPrice = priceService.getPrice(priceId)

        return existingPrice?.let {
            ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(priceService.patchPrice(it, updatePriceDto.price))
        } ?: run {
            throw PriceNotFoundException("존재 하지 않는 상품 가격 정보 입니다. (id: ${priceId})")
        }
    }

    /**
     * 브랜드 상품 가격 삭제
     */
    @DeleteMapping("/prices/{priceId}")
    fun deletePrice(@PathVariable("priceId") priceId: String): ResponseEntity<DeletePriceDto> {
        val existingPrice = priceService.getPrice(priceId)

        return existingPrice?.id?.let {
            ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(DeletePriceDto(priceService.delPrice(it)))
        } ?: run {
            throw PriceNotFoundException("존재 하지 않는 상품 가격 정보 입니다. (id: ${priceId})")
        }
    }
}
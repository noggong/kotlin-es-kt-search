package com.example.demo.controller

import com.example.demo.dto.*
import com.example.demo.entity.Price
import com.example.demo.exception.PriceNotFoundException
import com.example.demo.service.PriceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
    @GetMapping("/lowest-price/by/category")
    fun lowestPriceByCategory(): ResponseEntity<LowestPriceByCategoryResponseDto> {
        return ResponseEntity.ok().body(
            LowestPriceByCategoryResponseDto(
                listOf(
                    PriceIdless("상의","C", 10000),
                    PriceIdless("상의", "C", 10000)),
                34100
            )
        )
    }

    /**
     * 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
     */
    @GetMapping("lowest-price/by/brand")
    fun lowestPriceByBrand(): ResponseEntity<LowestPriceByBrandResponseDto> {
        return ResponseEntity.ok().body(
            LowestPriceByBrandResponseDto("D", 36100)
        )
    }

    /**
     * 각 카테고리 이름으로 최소, 최대 가격 조회 API
     */
    @GetMapping("price/by/{category}")
    fun priceByCategory(@PathVariable("category") category: String): ResponseEntity<PriceByCategoryResponseDto> {
        return ResponseEntity.ok().body(
            PriceByCategoryResponseDto(
                PriceIdless(category, "C", 10000),
                PriceIdless(category, "I", 11400))
        )
    }

    /**
     * 브랜드 상품 가격 추가
     */
    @PostMapping("/prices")
    fun addPrice(@RequestBody @Valid addPriceDto: AddPriceRequestDto): ResponseEntity<Price> {
        val priceDto = PriceDto(null, addPriceDto.category, addPriceDto.price, addPriceDto.brand)
        val newPriceEntity = priceService.addPrice(priceDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(newPriceEntity)
    }

    /**
     * 브랜드 상품 가격 업데이트
     */
    @PatchMapping("/prices/{priceId}")
    fun updatePrice(@PathVariable("priceId") priceId: Int,
        @RequestBody @Valid updatePriceDto: UpdatePriceRequestDto): ResponseEntity<Price> {
        val existingPrice = priceService.getPrice(priceId)

        return if (existingPrice.isPresent) {
            existingPrice.get().let {
                ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(priceService.patchPrice(it, updatePriceDto.price))
            }
        } else {
            throw PriceNotFoundException("존재 하지 않는 상품 가격 정보 입니다. (id: ${priceId})")
        }
    }

    /**
     * 브랜드 상품 가격 삭제
     */
    @DeleteMapping("/prices/{priceId}")
    fun deletePrice(@PathVariable("priceId") priceId: Int): ResponseEntity<DeletePriceResponseDto> {
        val existingPrice = priceService.getPrice(priceId)

        return if (existingPrice.isPresent) {
            existingPrice.get().let {
                priceService.delPrice(it)
                ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(DeletePriceResponseDto(priceId))
            }
        } else {
            throw PriceNotFoundException("존재 하지 않는 상품 가격 정보 입니다. (id: ${priceId})")
        }
    }
}
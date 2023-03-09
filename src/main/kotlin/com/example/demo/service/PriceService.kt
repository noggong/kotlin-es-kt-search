package com.example.demo.service

import com.example.demo.dto.PriceDto
import com.example.demo.entity.Price
import com.example.demo.repository.PriceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PriceService (
    val priceRepository: PriceRepository
    ) {

    /**
     * 가격을 추가한다.
     * @param priceDto PriceDto 추가될 가격의 정보 DTO
     * @return Price
     */
    fun addPrice(priceDto: PriceDto): Price {
        val priceEntity = priceDto.let {
            Price(null, priceDto.category, priceDto.price, priceDto.brand)
        }
        priceRepository.save(priceEntity)
        return priceEntity
    }

    /**
     * id 에 해당하는 price 정보를 가져온다.
     * @param priceId int price 정보의 id
     * @return Optional<price>
     */
    fun getPrice(priceId: Int): Optional<Price> = priceRepository.findById(priceId)

    /**
     * 특정 가격을 변경한다.
     * @param price Price 기존에 존재 하던 가격 entity
     * @param priceToChange int 변경될 가격
     * @return Price
     */
    fun patchPrice(price: Price, priceToChange: Int): Price {
        price.price = priceToChange
        return priceRepository.save(price)
    }

    /**
     * 특정 가격을 삭제 한다.
     * @param price Price 기존에 존재 하던 삭제될 가격 entity
     */
    fun delPrice(price: Price) {
        priceRepository.delete(price)
    }
}
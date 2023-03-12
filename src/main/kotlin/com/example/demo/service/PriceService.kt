package com.example.demo.service

import com.example.demo.document.CategoryAndStats
import com.example.demo.document.Price
import com.example.demo.dto.*
import com.example.demo.exception.PriceNotFoundException
import com.example.demo.repository.PriceRepository
import org.springframework.stereotype.Service

@Service
class PriceService(
    private val priceRepository: PriceRepository) {

    /**
     * 카테고리별 최저가와 최저가 브랜드 가져오기
     */
    fun lowestPriceByCategory(): LowestPriceByCategoryDto {
        val esResult = priceRepository.lowestPriceByCategory()

        if (esResult.lowestPrice.buckets.isEmpty()) {
            throw PriceNotFoundException("현재 상품이 존재 하지 않습니다.")
        }

        val prices = esResult.lowestPrice.buckets.map {
            PriceIdless(
                it.top.hits.hits[0]._source.category,
                it.top.hits.hits[0]._source.brand,
                it.top.hits.hits[0]._source.price,
            )
        }
        return LowestPriceByCategoryDto(prices, esResult.bucketStats.sum)
    }

    /**
     * 브랜드별 모든 카테고리의 최저가 상품가격을 합친 가격 중 최저가 가져오기
     */
    fun lowestPriceByBrand(): LowestPriceByBrandDto {
        val lowestOfSumPrices = priceRepository.lowestPriceByBrand()

        if (lowestOfSumPrices.pricePerBrand.buckets.isEmpty()) {
            throw PriceNotFoundException("현재 상품이 존재 하지 않습니다.")
        }
        val lowestPriceInfo = lowestOfSumPrices.pricePerBrand.buckets.reduce { acc: CategoryAndStats, cur: CategoryAndStats ->
            if (acc.bucketStats.sum > cur.bucketStats.sum) cur else acc
        }

        return LowestPriceByBrandDto(lowestPriceInfo.key, lowestPriceInfo.bucketStats.sum)
    }

    /**
     * 특정 카테고리의 최고가, 최저가 와 브랜드 가져오기
     */
    fun priceByCategory(category: String): PriceByCategoryDto  {
        val lowestAndHighestPrice = priceRepository.priceByCategory(category)

        if (lowestAndHighestPrice.highestPrice.hits.hits.isEmpty()) {
            throw PriceNotFoundException("현재 상품이 존재 하지 않습니다.")
        }

        val highest = lowestAndHighestPrice.highestPrice.hits.hits[0]._source
        val lowest = lowestAndHighestPrice.lowestPrice.hits.hits[0]._source

        return PriceByCategoryDto(
            PriceIdless(
                highest.category,
                highest.brand,
                highest.price,
            ),
            PriceIdless(
                lowest.category,
                lowest.brand,
                lowest.price,
            )
        )
    }

    /**
     * 가격을 추가한다.
     * @param priceDto PriceDto 추가될 가격의 정보 DTO
     * @return Price
     */
    fun addPrice(priceDto: PriceDto): Price = priceRepository.save(
        Price(null, priceDto.brand, priceDto.category, priceDto.price)
    )

    /**
     * id 에 해당하는 price 정보를 가져온다.
     * @param id String price 정보의 id
     * @return price?
     */
    fun getPrice(id: String): Price? = priceRepository.findById(id)

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
    fun delPrice(id: String): String {
        priceRepository.delete(id)
        return id
    }
}
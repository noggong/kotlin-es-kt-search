package com.example.demo.service

import com.example.demo.document.ElasticClient
import com.example.demo.document.Price
import com.example.demo.dto.PriceDto
import com.jillesvangurp.ktsearch.SearchClient
import com.jillesvangurp.ktsearch.ids
import com.jillesvangurp.ktsearch.search
import kotlinx.coroutines.*
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Service
import java.util.*

//    "aggs": {
//        "result": {
//            "terms": {
//            "field": "category"
//        },
//            "aggs": {
//            "lowest_price": {
//            "top_hits": {
//            "size": 1,
//            "sort": [{"price": {"order": "asc"}}],
//            "_source": {
//            "includes": ["brand", "price"]
//        }
//        }
//        }
//        }
//        }
//    }

@Service
class PriceService (
    val esOperations: ElasticsearchOperations,
    ) {
    fun lowestPriceByCategory() {
        val ids = runBlocking {
            ElasticClient().client.search("prices").ids
        }
        println(ids)

//        client.search("prices").ids


//
//        val aggByCategory = AggregationBuilders.terms("result").field("category")
//        val aggLowestPrice = TopHitsAggregationBuilder("lowestPrice")
//            .size(1)
//            .sort("price", SortOrder.ASC)
//
//        val query = NativeSearchQueryBuilder()
//            .withAggregations(aggByCategory)
//            .withPageable(PageRequest.of(0, 1000))
//            .build()
//
//        val searchHits = esOperations.search(query, Price::class.java)
//
//        println(searchHits)
//        val result = searchHits.aggregations
//

//        for(hit in searchHits) {
//            println(hit)
//        }
    }

//    /**
//     * 가격을 추가한다.
//     * @param priceDto PriceDto 추가될 가격의 정보 DTO
//     * @return Price
//     */
//    fun addPrice(priceDto: PriceDto): Price {
//        val priceDoc = priceDto.let {
//            Price(null, priceDto.brand, priceDto.category, priceDto.price)
//        }
//        priceESRepository.save(priceDoc)
//        return priceDoc
//    }
//
//    /**
//     * id 에 해당하는 price 정보를 가져온다.
//     * @param priceId String price 정보의 id
//     * @return Optional<price>
//     */
//    fun getPrice(priceId: String): Optional<Price> = priceESRepository.findById(priceId)
//
//    /**
//     * 특정 가격을 변경한다.
//     * @param price Price 기존에 존재 하던 가격 entity
//     * @param priceToChange int 변경될 가격
//     * @return Price
//     */
//    fun patchPrice(price: Price, priceToChange: Int): Price {
//        price.price = priceToChange
//        return priceESRepository.save(price)
//    }
//
//    /**
//     * 특정 가격을 삭제 한다.
//     * @param price Price 기존에 존재 하던 삭제될 가격 entity
//     */
//    fun delPrice(price: Price) {
//        priceESRepository.delete(price)
//    }
}
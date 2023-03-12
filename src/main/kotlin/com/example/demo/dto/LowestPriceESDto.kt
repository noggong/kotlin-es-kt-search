package com.example.demo.dto

data class LowestPriceESDto (
    val lowestPrice: LowestPriceAgg<LowestPriceBuckets>,
    val bucketStats: PriceStatsAgg,
)

data class PriceStatsAgg (
    val sum: Int
)

data class LowestPriceAgg<T> (
    val buckets: List<T>
)

data class LowestPriceBuckets (
    val key: String,
    val top: TopHits
)

data class TopHits (
   val hits: SortedHists
)

data class SortedHists (
    val hits: List<Source>
)

data class Source (
    val _source: PriceInfo
)

data class PriceInfo (
    val brand: String,
    val category: String,
    val price: Int,
)

data class PriceByCategoryESDto (
    val highestPrice: TopHits,
    val lowestPrice: TopHits,
)

data class LowestPriceByBrandESDto (
    val pricePerBrand: LowestPriceAgg<CategoryAndStats>,
)

data class CategoryAndStats (
    val key: String,
    val bucketStats: PriceStatsAgg
)

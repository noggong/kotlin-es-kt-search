package com.example.demo.document

data class LowestPriceTerms (
    val lowestPrice: LowestPriceAgg<LowestPriceBuckets>,
    val bucketStats: BucketStats,
)

data class BucketStats (
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

data class CategoryPriceAgg (
    val highestPrice: TopHits,
    val lowestPrice: TopHits,
)

data class BrandPriceAgg (
    val pricePerBrand: LowestPriceAgg<PriceCategoryTerms>,
)

data class PriceCategoryTerms (
    val key: String,
    val bucketStats: BucketStats
)

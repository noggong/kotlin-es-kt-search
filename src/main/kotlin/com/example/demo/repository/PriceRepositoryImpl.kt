package com.example.demo.repository

import com.example.demo.document.ElasticClient
import com.example.demo.document.Price
import com.example.demo.document.BrandPriceAgg
import com.example.demo.document.LowestPriceTerms
import com.example.demo.document.CategoryPriceAgg
import com.jillesvangurp.jsondsl.JsonDsl
import com.jillesvangurp.ktsearch.*
import com.jillesvangurp.searchdsls.querydsl.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.kotlin.com.google.gson.Gson
import org.springframework.stereotype.Component
import kotlin.reflect.KProperty


@Component
class PriceRepositoryImpl: PriceRepository {

    override fun lowestPriceByCategory(): LowestPriceTerms {
        val results = runBlocking {
            ElasticClient.client.search(ElasticClient.INDEX_NAME) {
                resultSize = 0
                agg("lowestPrice", TermsAgg(Price::category)) {
                    agg("top", TopHitsAgg() {
                        resultSize = 1
                        sort {
                            add("price", SortOrder.ASC)
                        }
                    })
                    agg("min", MinAgg(Price::price))
                }
                agg("bucketStats", ExtendedStatsBucketAgg {
                    bucketsPath = "lowestPrice>min"
                })
            }
        }

        return Gson().fromJson(results.aggregations?.toString()!!, LowestPriceTerms::class.java)
    }

    override fun lowestPriceByBrand(): BrandPriceAgg {

        val results = runBlocking {
            ElasticClient.client.search(ElasticClient.INDEX_NAME) {
                resultSize = 0
                agg("pricePerBrand", TermsAgg(Price::brand)) {
                    agg("pricePerCategory", TermsAgg(Price::category)) {
                        agg("minPrice", MinAgg(Price::price)) {

                        }
                    }
                    agg("bucketStats", ExtendedStatsBucketAgg {
                        bucketsPath = "pricePerCategory>minPrice"
                    })
                }
            }
        }
        return Gson().fromJson(results.aggregations?.toString()!!, BrandPriceAgg::class.java)

    }

    override fun priceByCategory(category: String): CategoryPriceAgg {
        val result = runBlocking {
            ElasticClient.client.search(ElasticClient.INDEX_NAME) {
                resultSize = 0
                query = match(Price::category, category)
                agg("lowestPrice", TopHitsAgg() {
                    resultSize = 1
                    sort {
                        add("price", SortOrder.ASC)
                    }
                })

                agg("highestPrice", TopHitsAgg() {
                    resultSize = 1
                    sort {
                        add("price", SortOrder.DESC)
                    }
                })
            }
        }

        return Gson().fromJson(result.aggregations?.toString()!!, CategoryPriceAgg::class.java)
    }

    override fun save(price: Price): Price {
        return price.id?.let {
            updateDocument(it, price)
            price
        } ?: run {
            val result = indexDocument(price)
            price.id = result.id
            price
        }
    }

    override fun findById(id: String): Price? {
        return try {
            val result = runBlocking {
                ElasticClient.client.getDocument(ElasticClient.INDEX_NAME, id)
            }
            val doc = Gson().fromJson(result.source.toString(), Price::class.java)
            doc.id = result.id
            return doc
        } catch (e: RestException ) {
            null
        }
    }

    override fun delete(id: String): Unit {
        runBlocking {
            ElasticClient.client.deleteDocument(ElasticClient.INDEX_NAME, id)
        }
    }

    private fun indexDocument(price: Price) = runBlocking {
        ElasticClient.client.indexDocument(ElasticClient.INDEX_NAME, price)
    }
    private fun updateDocument(id: String, price: Price) = runBlocking {
        ElasticClient.client.updateDocument(ElasticClient.INDEX_NAME, id, price)
    }
}
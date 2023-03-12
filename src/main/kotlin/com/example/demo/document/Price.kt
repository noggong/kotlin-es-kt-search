package com.example.demo.document

import com.jillesvangurp.ktsearch.KtorRestClient
import com.jillesvangurp.ktsearch.SearchClient
import kotlinx.serialization.Serializable

@Serializable
data class Price (
    var id: String?,
    var brand: String,
    var category: String,
    var price: Int,
)

class ElasticClient() {
    companion object {
        val INDEX_NAME = "prices"
        val client = SearchClient(KtorRestClient("http://es01", 9200))
    }
}


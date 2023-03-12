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
        val client = SearchClient(KtorRestClient("127.0.0.1", 9200))
    }


//    fun indexDocuments(documents: List<Price>) {
//        documents.chunked(1000).forEach {
//            runBlocking {
//                client.bulk {
//                    it.forEach { esDocument ->
//                        index(
//                            source = DEFAULT_JSON.encodeToString(Price.ser, esDocument),
//                            index = "prices",
//                            id = esDocument.id
//                        )
//                    }
//                }
//            }
//        }
//    }

}
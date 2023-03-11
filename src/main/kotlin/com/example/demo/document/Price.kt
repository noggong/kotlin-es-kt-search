package com.example.demo.document

import com.jillesvangurp.ktsearch.DEFAULT_JSON
import com.jillesvangurp.ktsearch.KtorRestClient
import com.jillesvangurp.ktsearch.SearchClient
import com.jillesvangurp.ktsearch.bulk
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable


@Serializable
data class Price (
    var id: String?,

//    @Field(type=FieldType.Keyword)
    var brand: String,

//    @Field(type=FieldType.Keyword)
    var category: String,

//    @Field(type=FieldType.Integer)
    var price: Int,
)

class ElasticClient() {
    val client = SearchClient(KtorRestClient("127.0.0.1", 9200))

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
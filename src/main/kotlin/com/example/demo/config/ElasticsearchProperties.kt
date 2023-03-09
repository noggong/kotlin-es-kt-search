package com.example.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "elasticsearch")
class ElasticsearchProperties(
    private val host: String,
    private val port: Int,
) {
    fun getHostAndPort(): String {
        return "$host:$port"
    }
}
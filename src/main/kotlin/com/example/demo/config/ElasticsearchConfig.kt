package com.example.demo.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories
class ElasticsearchConfig(
    var elasticsearchProperties: ElasticsearchProperties
) : AbstractElasticsearchConfiguration() {
    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration: ClientConfiguration = ClientConfiguration.builder()
            .connectedTo(elasticsearchProperties.getHostAndPort())
            .build()


        return RestClients.create(clientConfiguration).rest()
    }
}
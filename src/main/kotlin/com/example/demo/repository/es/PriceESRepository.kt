package com.example.demo.repository.es

import com.example.demo.document.Price
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PriceESRepository: ElasticsearchRepository<Price, String>

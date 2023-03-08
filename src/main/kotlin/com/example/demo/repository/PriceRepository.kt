package com.example.demo.repository

import com.example.demo.entity.Price
import org.springframework.data.jpa.repository.JpaRepository

interface PriceRepository: JpaRepository<Price, Int> {}
package com.example.demo.entity
import javax.persistence.*

@Entity
@Table(name="products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val 카테고리: String,
    val price: Int,
    ) {}
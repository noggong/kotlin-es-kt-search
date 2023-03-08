package com.example.demo.entity
import javax.persistence.*

@Entity
@Table(name="products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val category: String,
    val price: Int,
    val brand: String,
    ) {}
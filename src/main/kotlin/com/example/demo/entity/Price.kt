package com.example.demo.entity
import javax.persistence.*

@Entity
@Table(name="prices")
class Price(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val category: String,
    var price: Int,
    val brand: String,
    ) {}
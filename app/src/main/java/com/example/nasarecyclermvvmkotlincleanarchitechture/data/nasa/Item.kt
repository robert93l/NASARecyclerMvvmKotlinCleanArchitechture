package com.example.nasarecyclermvvmkotlincleanarchitechture.data.nasa

data class Item(
    val `data`: List<Data>,
    val href: String,
    val links: List<Link>
)
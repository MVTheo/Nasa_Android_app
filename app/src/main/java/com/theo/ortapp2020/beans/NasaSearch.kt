package com.theo.ortapp2020.beans

data class NasaSearch(
    val collection: Collection
)

data class Collection(
    val href: String,
    val items: ArrayList<Item>,
    val links: List<LinkX>,
    val metadata: Metadata,
    val version: String
)

data class Item(
    val `data`: List<Data>,
    val href: String,
    val links: List<Link>
)

data class Data(
    val album: List<String>,
    val center: String,
    val date_created: String,
    val description: String,
    val keywords: List<String>,
    val location: String,
    val media_type: String,
    val nasa_id: String,
    val photographer: String,
    val title: String
)

data class Link(
    val href: String,
    val rel: String,
    val render: String
)

data class LinkX(
    val href: String,
    val prompt: String,
    val rel: String
)

data class Metadata(
    val total_hits: Int
)
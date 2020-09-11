package com.hexamass.votingknowledge.model

data class Content(
    val id: String,
    val images: List<String>,
    val title: String,
    val body: String,
    val lang: String,
    val time: String,
    var leftImageCount: String
)

data class Contents(val contents: List<Content>)

data class ResponseContents(val payload: Contents)

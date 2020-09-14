package com.hexamass.votingknowledge.model

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("_id")
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

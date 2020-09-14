package com.hexamass.votingknowledge.model

import com.google.gson.annotations.SerializedName

data class ImageSet(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val images: List<String?>
)

data class Gallery(
    val payload: List<ImageSet>
)
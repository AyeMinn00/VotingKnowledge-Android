package com.hexamass.votingknowledge.model

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("_id")
    val id: String,
    val lang: String,
    var isSelected: Boolean = false
)

data class Languages(val payload: List<Language>)
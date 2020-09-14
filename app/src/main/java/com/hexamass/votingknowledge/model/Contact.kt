package com.hexamass.votingknowledge.model

import com.google.gson.annotations.SerializedName

data class Contact (
    @SerializedName("_id")
    val id : String ,
    val title : String,
    val description : String
)

data class Contacts(val payload : List<Contact>)
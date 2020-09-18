package com.hexamass.votingknowledge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Content(
    @SerializedName("_id")
    val id: String,
    val images: List<String>,
    val title: String,
    val body: String,
    val time: String,
    var leftImageCount: String
)

data class Contents(val contents: List<Content>)

data class ResponseContents(val payload: Contents)

fun formatContent(contents: List<Content>): List<Content> {
    contents.forEach {
        val size = it.images.size
        if (size > 1) {
            it.leftImageCount = "+ ${size - 1} more"
        }
    }
    return contents
}

@Parcelize
data class ParcelContent(
    val images: List<String>,
    val title: String,
    val body: String,
    val time: String
) : Parcelable

fun createParcelContent(content: Content) =
    ParcelContent(content.images, content.title, content.body, content.time)

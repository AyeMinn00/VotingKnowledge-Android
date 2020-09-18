package com.hexamass.votingknowledge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ImageSet(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val images: List<String?>,
    var viewHolderType: Int = 1,
    var imageLeftCount: String = ""
)

data class Gallery(
    val payload: List<ImageSet>
)

@Parcelize
data class ParcelImages(
    val images: List<String?>
) : Parcelable

fun formatImageSet(payload: List<ImageSet>?): List<ImageSet>? {
    payload?.forEach {
        when {
            it.images.size >= 4 -> {
                it.viewHolderType = 4
                it.imageLeftCount = "+ ${it.images.size - 3}"
            }
            it.images.isEmpty() -> {
                it.viewHolderType = 1
            }
            else -> it.viewHolderType = it.images.size
        }
    }
    return payload
}
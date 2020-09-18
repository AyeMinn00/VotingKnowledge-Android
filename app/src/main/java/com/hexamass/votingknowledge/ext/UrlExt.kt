package com.hexamass.votingknowledge.ext

import com.hexamass.votingknowledge.constants.ApiEndpoint.IMG_MEDIUM_URL
import com.hexamass.votingknowledge.constants.ApiEndpoint.IMG_ORIGINAL_URL
import com.hexamass.votingknowledge.constants.ApiEndpoint.IMG_SMALL_URL

fun String.originalImageUrl(): String {
    return "$IMG_ORIGINAL_URL$this"
}

fun String.mediumImageUrl(): String {
    return "$IMG_MEDIUM_URL$this"
}

fun String.smallImageUrl(): String {
    return "$IMG_SMALL_URL$this"
}
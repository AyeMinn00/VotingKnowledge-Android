package com.hexamass.votingknowledge.model

sealed class Result<out T : Any> {

    /**
     * class for Success response
     */
    data class Success<out T : Any>(val data: T?) : Result<T>()


    /**
     * class for Exception
     */
    data class Error(val exception: Exception) : Result<Nothing>()


}
package com.hexamass.votingknowledge.model


sealed class Response<out T : Any> {
    class Done<out T : Any>(val data: T?) : Response<T>()
    class Loading(val msg: String) : Response<Nothing>()
    class Error(val e: Exception) : Response<Nothing>()
}
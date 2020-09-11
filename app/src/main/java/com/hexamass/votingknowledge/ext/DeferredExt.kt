package com.hexamass.votingknowledge.ext

import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import com.hexamass.votingknowledge.model.Result


suspend inline fun <T : Any> Deferred<Response<T>>.getResult(): Result<T> {
    val response = await()
    if (response.isSuccessful) return Result.Success(response.body())
    return Result.Error(HttpException(response))
}
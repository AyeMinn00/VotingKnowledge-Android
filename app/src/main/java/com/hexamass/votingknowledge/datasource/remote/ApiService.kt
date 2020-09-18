package com.hexamass.votingknowledge.datasource.remote

import com.hexamass.votingknowledge.model.Contacts
import com.hexamass.votingknowledge.model.Gallery
import com.hexamass.votingknowledge.model.Languages
import com.hexamass.votingknowledge.model.ResponseContents
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("content")
    fun getContentListAsync(
        @Query("count") count: Int,
        @Query("page") page: Int,
        @Query("lang_id") langId: String
    ): Deferred<Response<ResponseContents>>

    @GET("content/search")
    fun getContentListByKeyAsync(
        @Query("limit") count: Int,
        @Query("page") page: Int,
        @Query("key") key: String,
        @Query("lang") langId: String
    ): Deferred<Response<ResponseContents>>

    @GET("imgSet/lang/{langId}")
    fun getImageSets(@Path("langId") langId : String) : Deferred<Response<Gallery>>

    @GET("language/")
    fun getLanguages() : Deferred<Response<Languages>>

    @GET("contact")
    fun getContacts() : Deferred<Response<Contacts>>

}
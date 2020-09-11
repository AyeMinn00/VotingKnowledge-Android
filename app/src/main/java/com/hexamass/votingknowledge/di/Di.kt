package com.hexamass.votingknowledge.di

import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.datasource.pref.IPref
import com.hexamass.votingknowledge.datasource.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSource(apiService: ApiService, pref: IPref) = DataSource(apiService, pref)

}
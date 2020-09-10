package com.hexamass.votingknowledge.di

import com.hexamass.votingknowledge.datasource.pref.IPref
import com.hexamass.votingknowledge.datasource.pref.Pref
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class PrefModule {

    @Singleton
    @Binds
    abstract fun providePref(pref: Pref): IPref

}
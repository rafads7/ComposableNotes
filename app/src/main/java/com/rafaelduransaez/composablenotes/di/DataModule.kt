package com.rafaelduransaez.composablenotes.di

import com.rafaelduransaez.composablenotes.framework.local.RoomDataSource
import com.rafaelduransaez.data.NotesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: RoomDataSource): NotesLocalDataSource
}
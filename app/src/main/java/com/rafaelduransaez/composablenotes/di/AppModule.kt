package com.rafaelduransaez.composablenotes.di

import android.app.Application
import androidx.room.Room
import com.rafaelduransaez.composablenotes.framework.database.NotesDatabase
import com.rafaelduransaez.composablenotes.ui.NotesApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: Application) = app as NotesApp

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NotesDatabase = Room.databaseBuilder(
        app,
        NotesDatabase::class.java,
        "github_repos_database"
    ).build()

    @Provides
    @Singleton
    fun provideNotesDao(db: NotesDatabase) = db.notesDao()

}
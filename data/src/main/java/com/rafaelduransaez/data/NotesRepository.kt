package com.rafaelduransaez.data

import com.rafaelduransaez.domain.UiNote
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val localDataSource: NotesLocalDataSource
) {

    fun list() = localDataSource.list()

    suspend fun save(note: UiNote) = localDataSource.save(note)
}
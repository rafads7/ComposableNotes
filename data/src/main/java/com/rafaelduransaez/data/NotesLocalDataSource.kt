package com.rafaelduransaez.data

import com.rafaelduransaez.domain.UiNote
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {

    fun list(): Flow<List<UiNote>>

    fun getById(id: Long): Flow<UiNote>

    suspend fun save(note: UiNote): Boolean
}
package com.rafaelduransaez.composablenotes.framework.local

import com.rafaelduransaez.composablenotes.framework.database.dao.NotesDao
import com.rafaelduransaez.composablenotes.utils.toNoteEntity
import com.rafaelduransaez.composablenotes.utils.toUiNote
import com.rafaelduransaez.data.NotesLocalDataSource
import com.rafaelduransaez.domain.UiNote
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val dao: NotesDao
): NotesLocalDataSource {
    override fun list() = dao.list().map { it.toUiNote() }

    override fun getById(id: Long) = dao.getById(id).map { it.toUiNote() }

    override suspend fun save(note: UiNote) = dao.upsert(note.toNoteEntity()) > 0

}
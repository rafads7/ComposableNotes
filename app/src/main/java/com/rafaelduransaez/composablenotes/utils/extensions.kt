package com.rafaelduransaez.composablenotes.utils

import com.rafaelduransaez.composablenotes.framework.database.entities.NoteEntity
import com.rafaelduransaez.domain.UiNote

fun NoteEntity.toUiNote() = UiNote(id, title, description)

fun List<NoteEntity>.toUiNote(): List<UiNote> = map { it.toUiNote() }

fun UiNote.toNoteEntity() = NoteEntity(id, title, description)
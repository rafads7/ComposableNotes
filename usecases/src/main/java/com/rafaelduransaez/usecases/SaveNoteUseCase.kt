package com.rafaelduransaez.usecases

import com.rafaelduransaez.data.NotesRepository
import com.rafaelduransaez.domain.UiNote
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: UiNote) = repository.save(note)
}
package com.rafaelduransaez.usecases

import com.rafaelduransaez.data.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    operator fun invoke() = repository.list()
}
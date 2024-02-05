package com.rafaelduransaez.composablenotes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelduransaez.domain.UiNote
import com.rafaelduransaez.usecases.GetNotesUseCase
import com.rafaelduransaez.usecases.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        viewModelScope.launch {
            getNotesUseCase()
                .catch { _ -> _state.update { it.copy(isError = true) } }
                .stateIn(viewModelScope)
                .collect { notes -> _state.update { it.copy(notes = notes) }}
        }
    }

    fun onCreateNoteClicked() {
        viewModelScope.launch {
            saveNoteUseCase(state.value.newNote)
        }
    }

    fun onDescriptionChanged(description: String) {
        _state.update { it.copy(newNote = it.newNote.copy(description = description)) }
    }

    fun onTitleChanged(title: String) {
        _state.update { it.copy(newNote = it.newNote.copy(title = title)) }
    }

    fun onDialogVisibilityChangeRequest() {
        _state.update { it.copy(newNote = UiNote()) }
        _state.update { it.copy(isShowingNoteDialog = !it.isShowingNoteDialog) }
    }

    data class UiState(
        val notes: List<UiNote> = emptyList(),
        val newNote: UiNote = UiNote(),
        val isLoading: Boolean = false,
        val isShowingNoteDialog: Boolean = false,
        val isError: Boolean = false
    )
}
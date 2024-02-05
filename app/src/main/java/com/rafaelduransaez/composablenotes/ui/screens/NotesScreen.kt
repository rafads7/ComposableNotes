package com.rafaelduransaez.composablenotes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.rafaelduransaez.domain.UiNote
import com.rafaelduransaez.composablenotes.R
import com.rafaelduransaez.composablenotes.ui.theme.MatteBlack
import com.rafaelduransaez.composablenotes.ui.theme.SoftBlack
import com.rafaelduransaez.composablenotes.ui.theme.SoftYellow
import com.rafaelduransaez.composablenotes.ui.viewmodel.NotesViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(viewModel: NotesViewModel) {

    val snackBarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopAbbBarComponent() },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onDialogVisibilityChangeRequest() },
                containerColor = Color.Yellow, contentColor = MatteBlack,
                shape = ShapeDefaults.ExtraLarge
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay

    ) {
        Box(
            modifier = Modifier
                .background(SoftBlack)
                .fillMaxSize()
                .padding(it)

        ) {
            NotesList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                notes = state.notes
            )
        }

        AddNoteDialog(
            showDialog = state.isShowingNoteDialog,
            onDismissRequest = { viewModel.onDialogVisibilityChangeRequest() },
            onConfirmRequest = {
                viewModel.onCreateNoteClicked()
                viewModel.onDialogVisibilityChangeRequest()
            },
            onTitleChanged = { viewModel.onTitleChanged(it) },
            onDescriptionChanged = { viewModel.onDescriptionChanged(it) },
            title = state.newNote.title,
            description = state.newNote.description
        )
    }

}

@Composable
fun NotesList(modifier: Modifier, notes: List<UiNote>) {

    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(notes) { note ->
            NoteItem(note = note)
        }
    }, modifier = modifier)
}

@Composable
fun NoteItem(note: UiNote) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(SoftYellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = note.title, fontWeight = FontWeight.Bold)
        VerticalSpacer(size = 4)
        Text(text = note.description)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAbbBarComponent() {

    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = context.getString(R.string.app_name), color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MatteBlack,
            actionIconContentColor = Color.Yellow,
            navigationIconContentColor = Color.Yellow
        ),
        actions = { },
        navigationIcon = { }
    )
}

@Composable
fun AddNoteDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    title: String = "",
    description: String = ""
) {

    val context = LocalContext.current

    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .background(SoftYellow)
                    .padding(16.dp)
            ) {
                DialogHeader(context.getString(R.string.str_new_note))

                DialogInput(label = context.getString(R.string.str_title),
                    value = title,
                    onTextChanged = { onTitleChanged(it) })

                DialogMultilineInput(
                    label = context.getString(R.string.str_description),
                    value = description,
                    onTextChanged = { onDescriptionChanged(it) },
                )

                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = context.getString(R.string.str_cancel))
                    }
                    TextButton(onClick = { onConfirmRequest() }) {
                        Text(text = context.getString(R.string.str_create))
                    }
                }
            }

        }
    }
}


@Composable
fun VerticalSpacer(size: Int) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
    )
}
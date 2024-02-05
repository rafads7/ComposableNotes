package com.rafaelduransaez.composablenotes.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rafaelduransaez.composablenotes.ui.theme.SoftBlack
import com.rafaelduransaez.composablenotes.ui.theme.SoftYellow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DialogHeader(title: String) {
    Text(text = title, fontWeight = FontWeight.Bold)
    VerticalSpacer(size = 4)
}
@Composable
fun DialogMultilineInput(
    label: String,
    value: String,
    onTextChanged: (String) -> Unit,
    scrollState: ScrollState = rememberScrollState(0),
    scope: CoroutineScope = rememberCoroutineScope()
) {


    LaunchedEffect(scrollState.maxValue) {
        scrollState.scrollTo(scrollState.maxValue)
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .verticalScroll(scrollState)
            .onFocusChanged {
                if (!it.isFocused) {
                    scope.launch {
                        scrollState.scrollTo(0)
                    }
                }
            },
        label = {
            Text(text = label, color = Color.Yellow)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = SoftYellow,
            unfocusedTextColor = SoftYellow,
            unfocusedIndicatorColor = Color.Yellow,
            focusedIndicatorColor = Color.Yellow,
            focusedContainerColor = SoftBlack,
            unfocusedContainerColor = SoftBlack
        ),
        singleLine = false,
        value = value, onValueChange = { onTextChanged(it) })

    VerticalSpacer(4)
}

@Composable
fun DialogInput(label: String, value: String, onTextChanged: (String) -> Unit) {

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(text = label, color = Color.Yellow)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = SoftYellow,
            unfocusedTextColor = SoftYellow,
            unfocusedIndicatorColor = Color.Yellow,
            focusedIndicatorColor = Color.Yellow,
            focusedContainerColor = SoftBlack,
            unfocusedContainerColor = SoftBlack
        ),
        singleLine = false,
        value = value, onValueChange = { onTextChanged(it) })

    VerticalSpacer(4)
}
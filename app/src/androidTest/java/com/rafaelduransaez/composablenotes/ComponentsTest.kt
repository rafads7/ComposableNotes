package com.rafaelduransaez.composablenotes

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ComponentsTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun firstTest() {
        rule.setContent {
            //NoteItem()
        }
    }
}
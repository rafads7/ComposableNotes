package com.rafaelduransaez.composablenotes.framework.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("Note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(defaultValue = "0") val id: Long,
    @ColumnInfo(defaultValue = "") val title: String,
    @ColumnInfo(defaultValue = "") val description: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP") val date: Date? = null
)
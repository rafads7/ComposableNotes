package com.rafaelduransaez.composablenotes.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafaelduransaez.composablenotes.framework.database.dao.NotesDao
import com.rafaelduransaez.composablenotes.framework.database.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
}
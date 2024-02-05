package com.rafaelduransaez.composablenotes.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafaelduransaez.composablenotes.framework.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM Note")
    fun list(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM Note WHERE :id = id")
    fun getById(id: Long): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: NoteEntity): Long

}
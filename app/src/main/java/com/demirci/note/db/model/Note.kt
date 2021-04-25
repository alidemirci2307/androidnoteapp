package com.demirci.note.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "note_user_id") val noteUserId: Long?,
    @ColumnInfo(name = "note_title") val noteTitle: String?,
    @ColumnInfo(name = "note_description") val noteDescription: String?,
    @ColumnInfo(name = "note_image") val noteImage: String?,
    @ColumnInfo(name = "state") var state: Int?,
    @ColumnInfo(name = "updated_date") val updated_date: Long = System.currentTimeMillis()
) : Serializable
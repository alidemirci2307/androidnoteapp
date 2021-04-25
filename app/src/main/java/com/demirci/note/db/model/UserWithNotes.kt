package com.demirci.note.db.model

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithNotes(
    @Embedded val user : User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "note_user_id"
    )
    val notes : List<Note>
)

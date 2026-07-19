package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "domains")
data class DomainEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,

    val displayOrder: Int
)

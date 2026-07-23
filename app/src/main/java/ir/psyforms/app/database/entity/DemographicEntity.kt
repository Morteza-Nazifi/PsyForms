package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "demographics",
    foreignKeys = [
        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["sessionId"], unique = true)
    ]
)
data class DemographicEntity(

    @androidx.room.PrimaryKey
    val sessionId: Long,

    val gender: Int,

    val age: Int,

    val educationLevel: Int,

    val fatherEducation: Int,

    val motherEducation: Int
)

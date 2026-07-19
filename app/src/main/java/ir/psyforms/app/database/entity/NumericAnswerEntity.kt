package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "numeric_answers",
    foreignKeys = [
        ForeignKey(
            entity = AnswerEntity::class,
            parentColumns = ["id"],
            childColumns = ["answerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("answerId")
    ]
)
data class NumericAnswerEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val answerId: Long,

    val value: Double
)

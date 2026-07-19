package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "option_answers",
    foreignKeys = [
        ForeignKey(
            entity = AnswerEntity::class,
            parentColumns = ["id"],
            childColumns = ["answerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = OptionItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["optionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("answerId"),
        Index("optionId")
    ]
)
data class OptionAnswerEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val answerId: Long,

    val optionId: Long
)

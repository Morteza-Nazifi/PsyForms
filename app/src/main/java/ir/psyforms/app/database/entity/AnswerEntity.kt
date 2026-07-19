package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(
            entity = QuestionnaireSessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionnaireSessionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("questionnaireSessionId"),
        Index("questionId")
    ]
)
data class AnswerEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val questionnaireSessionId: Long,

    val questionId: Long
)

package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questionnaire_sessions",
    foreignKeys = [
        ForeignKey(
            entity = AssessmentSessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = QuestionnaireEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionnaireId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("sessionId"),
        Index("questionnaireId")
    ]
)
data class QuestionnaireSessionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val sessionId: Long,

    val questionnaireId: Long,

    val startedAt: Long,

    val completedAt: Long?,

    val status: Int
)

package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scores",
    foreignKeys = [

        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sessionId"],
            onDelete = ForeignKey.NO_ACTION
        ),

        ForeignKey(
            entity = QuestionnaireEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionnaireId"],
            onDelete = ForeignKey.NO_ACTION
        ),

        ForeignKey(
            entity = SubscaleEntity::class,
            parentColumns = ["id"],
            childColumns = ["subscaleId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index("sessionId"),
        Index("questionnaireId"),
        Index("subscaleId")
    ]
)
data class ScoreEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val sessionId: Long,

    val questionnaireId: Long,

    /**
     * اگر null باشد یعنی نمره کل پرسشنامه.
     * در غیر این صورت نمره همان خرده‌مقیاس است.
     */
    val subscaleId: Long? = null,

    val score: Double
)

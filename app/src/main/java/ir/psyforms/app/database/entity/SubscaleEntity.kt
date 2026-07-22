package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "subscales",
    foreignKeys = [
        ForeignKey(
            entity = QuestionnaireEntity::class,
            parentColumns = ["id"],
            childColumns = ["questionnaireId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("questionnaireId"),
        Index(
            value = ["questionnaireId", "displayOrder"],
            unique = true
        )
    ]
)
data class SubscaleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val questionnaireId: Long,

    val name: String,

    val description: String,

    val displayOrder: Int
)

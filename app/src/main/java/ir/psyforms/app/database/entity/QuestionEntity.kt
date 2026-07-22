package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = SubscaleEntity::class,
            parentColumns = ["id"],
            childColumns = ["subscaleId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = OptionTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["optionTemplateId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("subscaleId"),
        Index("optionTemplateId"),
        Index(
            value = ["subscaleId", "displayOrder"],
            unique = true
        )
    ]
)
data class QuestionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val subscaleId: Long,

    val optionTemplateId: Long? = null,

    val questionType: Int,

    val title: String,

    val displayOrder: Int
)

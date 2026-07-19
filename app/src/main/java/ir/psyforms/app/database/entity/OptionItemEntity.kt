package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "option_items",
    foreignKeys = [
        ForeignKey(
            entity = OptionTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("templateId")
    ]
)
data class OptionItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val templateId: Long,

    val title: String,

    val score: Int,

    val displayOrder: Int
)

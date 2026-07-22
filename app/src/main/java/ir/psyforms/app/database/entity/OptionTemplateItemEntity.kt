package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "option_template_items",
    foreignKeys = [
        ForeignKey(
            entity = OptionTemplateEntity::class,
            parentColumns = ["id"],
            childColumns = ["templateId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("templateId"),
        Index(
            value = ["templateId", "displayOrder"],
            unique = true
        )
    ]
)
data class OptionTemplateItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val templateId: Long,

    val displayOrder: Int,

    val title: String,

    val score: Double
)

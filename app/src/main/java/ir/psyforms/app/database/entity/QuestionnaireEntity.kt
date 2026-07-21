package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "questionnaires",
    foreignKeys = [
        ForeignKey(
            entity = DomainEntity::class,
            parentColumns = ["id"],
            childColumns = ["domainId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("domainId"),
        Index("displayOrder")
    ]
)
data class QuestionnaireEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val domainId: Long,

    val name: String,

    val description: String,

    val displayOrder: Int,

    val hasTotalScore: Boolean,

    val isActive: Boolean = true
)

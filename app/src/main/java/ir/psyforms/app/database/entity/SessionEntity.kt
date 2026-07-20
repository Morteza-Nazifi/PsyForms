package ir.psyforms.app.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sessions",
    indices = [
        Index(value = ["status"])
    ]
)
data class SessionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val startDateTime: Long,

    val finishDateTime: Long? = null,

    /**
     * 0 = Draft
     * 1 = Completed
     */
    val status: Int = STATUS_DRAFT

) {

    companion object {
        const val STATUS_DRAFT = 0
        const val STATUS_COMPLETED = 1
    }
}

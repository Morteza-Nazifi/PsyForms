package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.ResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(response: ResponseEntity): Long

    @Update
    suspend fun update(response: ResponseEntity)

    @Query("SELECT * FROM responses WHERE id = :id")
    suspend fun getById(id: Long): ResponseEntity?

    @Query(
        """
        SELECT * FROM responses
        WHERE sessionId = :sessionId
        ORDER BY questionnaireId, subscaleId, questionId
        """
    )
    fun getBySession(sessionId: Long): Flow<List<ResponseEntity>>

    @Query(
        """
        SELECT * FROM responses
        WHERE sessionId = :sessionId
        AND questionId = :questionId
        LIMIT 1
        """
    )
    suspend fun getBySessionAndQuestion(
        sessionId: Long,
        questionId: Long
    ): ResponseEntity?

    @Query(
        """
        DELETE FROM responses
        WHERE sessionId = :sessionId
        """
    )
    suspend fun deleteBySession(sessionId: Long)
}

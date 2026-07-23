package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.ResponseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResponseDao {

    @Insert
    suspend fun insert(response: ResponseEntity): Long

    @Update
    suspend fun update(response: ResponseEntity)

    @Delete
    suspend fun delete(response: ResponseEntity)

    @Query("SELECT * FROM responses")
    fun getAll(): Flow<List<ResponseEntity>>

    @Query("SELECT * FROM responses WHERE id = :id")
    suspend fun getById(id: Long): ResponseEntity?

    @Query("""
        SELECT * FROM responses
        WHERE sessionId = :sessionId
        ORDER BY questionnaireId, subscaleId, questionId
    """)
    fun getBySession(sessionId: Long): Flow<List<ResponseEntity>>

    @Query("""
        SELECT * FROM responses
        WHERE questionId = :questionId
        AND sessionId = :sessionId
        LIMIT 1
    """)
    suspend fun getByQuestion(
        sessionId: Long,
        questionId: Long
    ): ResponseEntity?

    @Query("""
        DELETE FROM responses
        WHERE sessionId = :sessionId
    """)
    suspend fun deleteBySession(sessionId: Long)
}

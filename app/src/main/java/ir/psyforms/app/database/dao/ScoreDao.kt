package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.ScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Insert
    suspend fun insert(score: ScoreEntity): Long

    @Update
    suspend fun update(score: ScoreEntity)

    @Delete
    suspend fun delete(score: ScoreEntity)

    @Query("SELECT * FROM scores")
    fun getAll(): Flow<List<ScoreEntity>>

    @Query("SELECT * FROM scores WHERE id = :id")
    suspend fun getById(id: Long): ScoreEntity?

    @Query("""
        SELECT * FROM scores
        WHERE sessionId = :sessionId
        ORDER BY questionnaireId, subscaleId
    """)
    fun getBySession(sessionId: Long): Flow<List<ScoreEntity>>

    @Query("""
        SELECT * FROM scores
        WHERE sessionId = :sessionId
        AND questionnaireId = :questionnaireId
        AND subscaleId IS NULL
        LIMIT 1
    """)
    suspend fun getQuestionnaireScore(
        sessionId: Long,
        questionnaireId: Long
    ): ScoreEntity?

    @Query("""
        SELECT * FROM scores
        WHERE sessionId = :sessionId
        AND questionnaireId = :questionnaireId
        AND subscaleId = :subscaleId
        LIMIT 1
    """)
    suspend fun getSubscaleScore(
        sessionId: Long,
        questionnaireId: Long,
        subscaleId: Long
    ): ScoreEntity?

    @Query("""
        DELETE FROM scores
        WHERE sessionId = :sessionId
    """)
    suspend fun deleteBySession(sessionId: Long)
}

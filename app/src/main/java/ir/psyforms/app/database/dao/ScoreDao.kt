package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.ScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: ScoreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scores: List<ScoreEntity>)

    @Update
    suspend fun update(score: ScoreEntity)

    @Delete
    suspend fun delete(score: ScoreEntity)

    @Query("SELECT * FROM scores WHERE id = :id")
    suspend fun getById(id: Long): ScoreEntity?

    @Query("""
        SELECT *
        FROM scores
        WHERE sessionId = :sessionId
        ORDER BY questionnaireId, subscaleId
    """)
    fun observeBySession(sessionId: Long): Flow<List<ScoreEntity>>

    @Query("""
        SELECT *
        FROM scores
        WHERE sessionId = :sessionId
          AND questionnaireId = :questionnaireId
        ORDER BY subscaleId
    """)
    fun observeByQuestionnaire(
        sessionId: Long,
        questionnaireId: Long
    ): Flow<List<ScoreEntity>>

    @Query("""
        SELECT *
        FROM scores
        WHERE sessionId = :sessionId
          AND questionnaireId = :questionnaireId
          AND subscaleId IS NULL
        LIMIT 1
    """)
    suspend fun getTotalScore(
        sessionId: Long,
        questionnaireId: Long
    ): ScoreEntity?

    @Query("""
        SELECT *
        FROM scores
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

    @Query("DELETE FROM scores WHERE sessionId = :sessionId")
    suspend fun deleteBySession(sessionId: Long)
}

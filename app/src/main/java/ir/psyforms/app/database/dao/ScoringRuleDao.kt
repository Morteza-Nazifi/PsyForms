package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.ScoringRuleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoringRuleDao {

    @Insert
    suspend fun insert(rule: ScoringRuleEntity): Long

    @Update
    suspend fun update(rule: ScoringRuleEntity)

    @Delete
    suspend fun delete(rule: ScoringRuleEntity)

    @Query(
        """
        SELECT *
        FROM scoring_rules
        WHERE subscaleId = :subscaleId
        """
    )
    fun getBySubscaleId(subscaleId: Long): Flow<List<ScoringRuleEntity>>

    @Query(
        """
        SELECT *
        FROM scoring_rules
        WHERE questionId = :questionId
        LIMIT 1
        """
    )
    suspend fun getByQuestionId(questionId: Long): ScoringRuleEntity?

    @Query(
        """
        SELECT *
        FROM scoring_rules
        WHERE id = :id
        LIMIT 1
        """
    )
    suspend fun getById(id: Long): ScoringRuleEntity?

    @Query("SELECT * FROM scoring_rules")
    fun getAll(): Flow<List<ScoringRuleEntity>>
}

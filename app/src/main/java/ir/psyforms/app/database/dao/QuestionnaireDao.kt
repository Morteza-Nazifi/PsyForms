package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.QuestionnaireEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionnaireDao {

    @Insert
    suspend fun insert(questionnaire: QuestionnaireEntity): Long

    @Update
    suspend fun update(questionnaire: QuestionnaireEntity)

    @Delete
    suspend fun delete(questionnaire: QuestionnaireEntity)

    @Query("""
        SELECT *
        FROM questionnaires
        ORDER BY displayOrder
    """)
    fun getAll(): Flow<List<QuestionnaireEntity>>

    @Query("""
        SELECT *
        FROM questionnaires
        WHERE isActive = 1
        ORDER BY displayOrder
    """)
    fun getActive(): Flow<List<QuestionnaireEntity>>

    @Query("""
        SELECT *
        FROM questionnaires
        WHERE domainId = :domainId
        ORDER BY displayOrder
    """)
    fun getByDomain(domainId: Long): Flow<List<QuestionnaireEntity>>

    @Query("""
        SELECT *
        FROM questionnaires
        WHERE domainId = :domainId
          AND isActive = 1
        ORDER BY displayOrder
    """)
    fun getActiveByDomain(domainId: Long): Flow<List<QuestionnaireEntity>>

    @Query("""
        SELECT *
        FROM questionnaires
        WHERE id = :id
    """)
    suspend fun getById(id: Long): QuestionnaireEntity?
}

package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert
    suspend fun insert(question: QuestionEntity): Long

    @Update
    suspend fun update(question: QuestionEntity)

    @Delete
    suspend fun delete(question: QuestionEntity)

    @Query(
        """
        SELECT *
        FROM questions
        WHERE subscaleId = :subscaleId
        ORDER BY displayOrder
        """
    )
    fun getQuestionsBySubscale(
        subscaleId: Long
    ): Flow<List<QuestionEntity>>

    @Query(
        """
        SELECT *
        FROM questions
        WHERE id = :id
        LIMIT 1
        """
    )
    suspend fun getById(
        id: Long
    ): QuestionEntity?
}

package ir.psyforms.app.repository

import ir.psyforms.app.database.dao.QuestionDao
import ir.psyforms.app.database.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow

class QuestionRepository(
    private val questionDao: QuestionDao
) {

    fun getAllQuestions(): Flow<List<QuestionEntity>> =
        questionDao.getAll()

    fun getQuestionsBySubscale(
        subscaleId: Long
    ): Flow<List<QuestionEntity>> =
        questionDao.getBySubscale(subscaleId)

    suspend fun getById(id: Long): QuestionEntity? =
        questionDao.getById(id)

    suspend fun insert(question: QuestionEntity): Long =
        questionDao.insert(question)

    suspend fun update(question: QuestionEntity) =
        questionDao.update(question)

    suspend fun delete(question: QuestionEntity) =
        questionDao.delete(question)
}

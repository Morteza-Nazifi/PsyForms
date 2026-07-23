package ir.psyforms.app.repository

import ir.psyforms.app.database.dao.ScoreDao
import ir.psyforms.app.database.entity.ScoreEntity
import kotlinx.coroutines.flow.Flow

class ScoreRepository(
    private val scoreDao: ScoreDao
) {

    suspend fun insert(score: ScoreEntity): Long {
        return scoreDao.insert(score)
    }

    suspend fun update(score: ScoreEntity) {
        scoreDao.update(score)
    }

    suspend fun delete(score: ScoreEntity) {
        scoreDao.delete(score)
    }

    fun getAll(): Flow<List<ScoreEntity>> {
        return scoreDao.getAll()
    }

    suspend fun getById(id: Long): ScoreEntity? {
        return scoreDao.getById(id)
    }

    fun getBySession(sessionId: Long): Flow<List<ScoreEntity>> {
        return scoreDao.getBySession(sessionId)
    }

    suspend fun getQuestionnaireScore(
        sessionId: Long,
        questionnaireId: Long
    ): ScoreEntity? {
        return scoreDao.getQuestionnaireScore(
            sessionId,
            questionnaireId
        )
    }

    suspend fun getSubscaleScore(
        sessionId: Long,
        questionnaireId: Long,
        subscaleId: Long
    ): ScoreEntity? {
        return scoreDao.getSubscaleScore(
            sessionId,
            questionnaireId,
            subscaleId
        )
    }

    suspend fun deleteBySession(sessionId: Long) {
        scoreDao.deleteBySession(sessionId)
    }
}

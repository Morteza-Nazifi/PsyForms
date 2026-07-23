package ir.psyforms.app.repository

import ir.psyforms.app.database.dao.SessionDao
import ir.psyforms.app.database.entity.SessionEntity
import kotlinx.coroutines.flow.Flow

class SessionRepository(
    private val sessionDao: SessionDao
) {

    suspend fun insert(session: SessionEntity): Long {
        return sessionDao.insert(session)
    }

    suspend fun update(session: SessionEntity) {
        sessionDao.update(session)
    }

    suspend fun delete(session: SessionEntity) {
        sessionDao.delete(session)
    }

    fun getAll(): Flow<List<SessionEntity>> {
        return sessionDao.getAll()
    }

    suspend fun getById(id: Long): SessionEntity? {
        return sessionDao.getById(id)
    }

    fun getByStatus(status: Int): Flow<List<SessionEntity>> {
        return sessionDao.getByStatus(status)
    }

    suspend fun getLatestSession(): SessionEntity? {
        return sessionDao.getLatestSession()
    }
}

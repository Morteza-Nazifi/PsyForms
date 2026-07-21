package ir.psyforms.app.repository

import ir.psyforms.app.database.dao.DomainDao
import ir.psyforms.app.database.entity.DomainEntity
import kotlinx.coroutines.flow.Flow

class DomainRepository(
    private val domainDao: DomainDao
) {

    fun getAllDomains(): Flow<List<DomainEntity>> =
        domainDao.getAll()

    fun getActiveDomains(): Flow<List<DomainEntity>> =
        domainDao.getActive()

    suspend fun getDomainById(id: Long): DomainEntity? =
        domainDao.getById(id)

    suspend fun insertDomain(domain: DomainEntity): Long =
        domainDao.insert(domain)

    suspend fun updateDomain(domain: DomainEntity) =
        domainDao.update(domain)

    suspend fun deleteDomain(domain: DomainEntity) =
        domainDao.delete(domain)
}

package ir.psyforms.app.repository

import ir.psyforms.app.database.dao.OptionTemplateDao
import ir.psyforms.app.database.entity.OptionTemplateEntity
import kotlinx.coroutines.flow.Flow

class OptionTemplateRepository(
    private val optionTemplateDao: OptionTemplateDao
) {

    fun getAllOptionTemplates(): Flow<List<OptionTemplateEntity>> =
        optionTemplateDao.getAll()

    suspend fun getById(id: Long): OptionTemplateEntity? =
        optionTemplateDao.getById(id)

    suspend fun insert(optionTemplate: OptionTemplateEntity): Long =
        optionTemplateDao.insert(optionTemplate)

    suspend fun update(optionTemplate: OptionTemplateEntity) =
        optionTemplateDao.update(optionTemplate)

    suspend fun delete(optionTemplate: OptionTemplateEntity) =
        optionTemplateDao.delete(optionTemplate)
}

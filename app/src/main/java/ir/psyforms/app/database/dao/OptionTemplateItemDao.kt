package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.OptionTemplateItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionTemplateItemDao {

    @Insert
    suspend fun insert(item: OptionTemplateItemEntity): Long

    @Update
    suspend fun update(item: OptionTemplateItemEntity)

    @Delete
    suspend fun delete(item: OptionTemplateItemEntity)

    @Query("""
        SELECT * FROM option_template_items
        WHERE templateId = :templateId
        ORDER BY displayOrder
    """)
    fun getByTemplateId(templateId: Long): Flow<List<OptionTemplateItemEntity>>

    @Query("SELECT * FROM option_template_items WHERE id = :id")
    suspend fun getById(id: Long): OptionTemplateItemEntity?
}

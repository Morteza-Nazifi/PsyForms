package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.SubscaleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscaleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subscale: SubscaleEntity): Long

    @Update
    suspend fun update(subscale: SubscaleEntity)

    @Delete
    suspend fun delete(subscale: SubscaleEntity)

    @Query(
        """
        SELECT *
        FROM subscales
        WHERE questionnaireId = :questionnaireId
        ORDER BY displayOrder
        """
    )
    fun getByQuestionnaire(questionnaireId: Long): Flow<List<SubscaleEntity>>

    @Query("SELECT * FROM subscales WHERE id = :id")
    suspend fun getById(id: Long): SubscaleEntity?
}

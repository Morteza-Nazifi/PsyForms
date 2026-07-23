package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.DemographicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DemographicDao {

    @Insert
    suspend fun insert(demographic: DemographicEntity): Long

    @Update
    suspend fun update(demographic: DemographicEntity)

    @Delete
    suspend fun delete(demographic: DemographicEntity)

    @Query("SELECT * FROM demographics")
    fun getAll(): Flow<List<DemographicEntity>>

    @Query("SELECT * FROM demographics WHERE sessionId = :sessionId")
    suspend fun getBySessionId(sessionId: Long): DemographicEntity?
}

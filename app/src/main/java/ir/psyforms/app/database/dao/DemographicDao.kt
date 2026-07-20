package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.DemographicEntity

@Dao
interface DemographicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(demographic: DemographicEntity)

    @Update
    suspend fun update(demographic: DemographicEntity)

    @Query("SELECT * FROM demographics WHERE sessionId = :sessionId")
    suspend fun getBySessionId(sessionId: Long): DemographicEntity?

    @Query("DELETE FROM demographics WHERE sessionId = :sessionId")
    suspend fun deleteBySessionId(sessionId: Long)
}

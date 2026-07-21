package ir.psyforms.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.psyforms.app.database.entity.DomainEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DomainDao {

    @Insert
    suspend fun insert(domain: DomainEntity): Long

    @Update
    suspend fun update(domain: DomainEntity)

    @Delete
    suspend fun delete(domain: DomainEntity)

    @Query("SELECT * FROM domains ORDER BY displayOrder")
    fun getAll(): Flow<List<DomainEntity>>

    @Query(
        """
        SELECT *
        FROM domains
        WHERE isActive = 1
        ORDER BY displayOrder
        """
    )
    fun getActive(): Flow<List<DomainEntity>>

    @Query("SELECT * FROM domains WHERE id = :id")
    suspend fun getById(id: Long): DomainEntity?
}

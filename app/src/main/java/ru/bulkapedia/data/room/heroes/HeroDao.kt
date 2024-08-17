package ru.bulkapedia.data.room.heroes

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Query("SELECT * FROM heroes")
    fun listenAll(): Flow<List<HeroWithFraction>>

    @Transaction
    @Query("SELECT * FROM heroes")
    suspend fun fetchAll(): List<HeroWithFraction>

    @Upsert
    suspend fun upsertFraction(fractionDto: FractionDto)

    @Upsert
    suspend fun upsertHero(heroDto: HeroDto)

}
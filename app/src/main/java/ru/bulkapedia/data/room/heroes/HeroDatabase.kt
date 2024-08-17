package ru.bulkapedia.data.room.heroes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FractionDto::class,  HeroDto::class],
    version = 2
)
abstract class HeroDatabase : RoomDatabase() {
    abstract val dao: HeroDao

    companion object {
        const val NAME = "hero"
    }
}
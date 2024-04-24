package com.example.lab6

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import java.util.Date

@Entity(tableName = "Crime")
data class Crime(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "isSolved") var isSolved: Boolean = false
)

@Dao
interface CrimeDao {
    @Insert(entity = Crime::class)
    fun insertNewCrime(newCrime: Crime)

    @Query("SELECT * FROM Crime")
    fun getAllCrimes():List<Crime>

    @Query("DELETE FROM Crime WHERE id = :id")
    fun deleteCrime(id: Int)
}

@Database(entities = [Crime::class], version = 1, exportSchema = false)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun insertNewCrime(): CrimeDao
    abstract fun getAllCrimes(): CrimeDao
    abstract fun deleteCrime(): CrimeDao
}
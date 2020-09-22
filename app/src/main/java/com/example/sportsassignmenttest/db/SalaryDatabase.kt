package com.example.sportsassignmenttest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportsassignmenttest.model.Salary

@Database(entities = [Salary::class], version = 1, exportSchema = false)
abstract class SalaryDatabase : RoomDatabase() {

    abstract fun salaryDao() : SalaryDAO

    companion object{
        @Volatile
        private var INSTANCE : SalaryDatabase? = null

        fun getDatabase(context: Context) : SalaryDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SalaryDatabase::class.java,
                    "salary_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
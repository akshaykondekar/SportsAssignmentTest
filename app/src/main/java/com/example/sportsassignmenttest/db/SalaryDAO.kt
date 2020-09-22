package com.example.sportsassignmenttest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportsassignmenttest.model.Salary

@Dao
interface SalaryDAO {
    //insert item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalary(salary: Salary)

    //update item
    @Update
    suspend fun updateSalary(salary: Salary)

    //delete item
    @Delete
    suspend fun deleteSalary(salary: Salary)

    //get all item
    @Query("SELECT * FROM salary ORDER BY id ASC")
    fun getAllSalary() : LiveData<List<Salary>>
}
package com.example.sportsassignmenttest.db

import androidx.lifecycle.LiveData
import com.example.sportsassignmenttest.model.Salary

class SalaryRepository(private val salaryDAO: SalaryDAO) {

    val getAllSalary : LiveData<List<Salary>> = salaryDAO.getAllSalary()

    suspend fun insertSalary(salary: Salary) = salaryDAO.insertSalary(salary)

    suspend fun updateSalary(salary: Salary) = salaryDAO.updateSalary(salary)

    suspend fun deleteSalary(salary: Salary) = salaryDAO.deleteSalary(salary)
}
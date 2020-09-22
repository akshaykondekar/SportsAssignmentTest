package com.example.sportsassignmenttest.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsassignmenttest.db.SalaryDatabase
import com.example.sportsassignmenttest.db.SalaryRepository
import com.example.sportsassignmenttest.model.Salary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SalaryViewModel(application: Application) : AndroidViewModel(application) {

    private val salaryDAO = SalaryDatabase.getDatabase(application).salaryDao()
    private val repository : SalaryRepository

    init {
        repository = SalaryRepository(salaryDAO)
    }

    fun getAllSalary() = repository.getAllSalary

    fun insertSalary(salary: Salary){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSalary(salary)
        }
    }

    fun updateSalary(salary: Salary){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSalary(salary)
        }
    }

    fun deleteSalary(salary: Salary){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSalary(salary)
        }
    }
}
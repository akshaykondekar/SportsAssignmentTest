package com.example.sportsassignmenttest.ui.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sportsassignmenttest.model.Salary

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase : MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(salaryList : List<Salary>){
        emptyDatabase.value = salaryList.isEmpty()
    }

    fun validateData(name : String, designation : String): Boolean{
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(designation)){
            false
        }
        else !(name.isEmpty() || designation.isEmpty())
    }
}
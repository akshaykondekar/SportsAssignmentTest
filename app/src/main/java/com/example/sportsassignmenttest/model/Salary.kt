package com.example.sportsassignmenttest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salary")
data class Salary(
    var name : String?,
    var salary : Double?,
    var designation : String?
) {
    @PrimaryKey(autoGenerate = true)
    var id : Long? = null
}
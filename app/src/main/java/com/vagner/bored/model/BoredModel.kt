package com.vagner.bored.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Bored")
data class BoredModel(
    @ColumnInfo(name = "accessibility")
    val accessibility: Double,
    @ColumnInfo(name = "activity")
    val activity: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var key: Int,
    @ColumnInfo(name = "participants")
    var participants: Int,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "progress")
    var progress: String,
    @ColumnInfo(name = "data")
    var data : String,
    @ColumnInfo(name = "end")
    var end : String
)

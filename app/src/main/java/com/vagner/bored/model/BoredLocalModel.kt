package com.vagner.bored.model

import androidx.annotation.ColorRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vagner.bored.R
import com.vagner.bored.util.Date

@Entity(tableName = "Bored")
data class BoredLocalModel(
    val accessibility: Double,
    val activity: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val key: Int,
    val participants: Int,
    val price: Double,
    val type: String,
    var progress: ProgressColor = ProgressColor.INPROGRESS,
    val data: String = Date.setDate(),
    var end: String = ""
)

enum class ProgressColor(@ColorRes val color: Int) {
    INPROGRESS(R.color.sunny), FINISHED(R.color.neon_green), CANCELED(R.color.red)
}

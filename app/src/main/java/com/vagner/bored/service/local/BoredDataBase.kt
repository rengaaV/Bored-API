package com.vagner.bored.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vagner.bored.model.BoredModel
import com.vagner.bored.util.Constants.NAME_DB

@Database(entities = [BoredModel::class], version = 1, exportSchema = false)
abstract class BoredDataBase : RoomDatabase() {
    abstract fun boredDao(): BoredDao

    companion object {
        private lateinit var INSTANCE: BoredDataBase

        fun getDataBase(context: Context): BoredDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(BoredDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, BoredDataBase::class.java, NAME_DB).allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}
package com.vagner.bored.util

import java.util.*

object Date {

    fun setDate(): String {
        val date = Calendar.getInstance().time
        return date.toString()
    }
}
package com.example.sleeptracker2

import android.app.Application

class MyApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}
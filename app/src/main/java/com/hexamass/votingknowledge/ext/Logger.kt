package com.hexamass.votingknowledge.ext

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

fun AppCompatActivity.log(msg: String = "") {
    Log.e(javaClass.name, msg)
}

fun AppCompatActivity.log(msg: Any?) {
    Log.e(javaClass.name, msg.toString())
}

fun AppCompatActivity.log(exception: Exception) {
    Log.e(javaClass.name, exception.message!!)
}

fun ViewModel.log(msg: String = "") {
    Log.e(javaClass.name, msg)
}

fun ViewModel.log(msg: Any?) {
    Log.e(javaClass.name, msg.toString())
}

fun ViewModel.log(exception: Exception) {
    Log.e(javaClass.name, exception.message!!)
}

fun Any.log(msg: String = "") {
    Log.e(javaClass.name, msg)
}
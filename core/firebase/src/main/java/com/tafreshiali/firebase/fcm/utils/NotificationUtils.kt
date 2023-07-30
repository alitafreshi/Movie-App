package com.tafreshiali.firebase.fcm.utils

import java.util.Random

fun extractValue(map: Map<String, String>, keyName: String, defaultValue: String): String =
    map[keyName] ?: defaultValue


fun extractValue(map: Map<String, String>, keyName: String, defaultValue: Int): Int =
    map[keyName]?.toIntOrNull() ?: defaultValue


fun getRandomId(): Int = Random(System.currentTimeMillis()).nextInt()

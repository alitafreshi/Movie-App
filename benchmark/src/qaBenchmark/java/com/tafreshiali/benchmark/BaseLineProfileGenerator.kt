package com.tafreshiali.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RequiresApi(Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
class BaseLineProfileGenerator {
    @get:Rule
    val baseLineRule = BaselineProfileRule()

    @Test
    fun generateBaseLineProfile() = baseLineRule.collect(
        packageName = "com.tafreshiali.moviewapp"
    ) {
        pressHome()
        startActivityAndWait()
        acceptNotificationPermission()
        scrollTrendingMoviesList()
    }
}
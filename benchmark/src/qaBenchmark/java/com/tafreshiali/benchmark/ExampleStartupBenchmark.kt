package com.tafreshiali.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 *
 *
 * ## Important Note :
 * ### [refer to this link for finding the objects in the screen](https://developer.android.com/jetpack/compose/testing#uiautomator-interop)
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.tafreshiali.moviewapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.WARM
    ) {
        pressHome()
        startActivityAndWait()
        val mainList = device.waitAndFindObject(By.res("myLazyColumn"), 10_000)
        val btnBanner = device.waitAndFindObject(By.desc("btn_banner"), 500)
    }

    @Test
    fun acceptNotificationDialog() = benchmarkRule.measureRepeated(
        packageName = "com.tafreshiali.moviewapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        val tvAllow = device.waitAndFindObject(By.text("Allow"), 5000)
        tvAllow.let {
            it.click()
            device.wait(Until.gone(By.text("Allow")), 1000)
        }
    }

    @Test
    fun scrollTrendingMoviePager() = benchmarkRule.measureRepeated(
        packageName = "com.tafreshiali.moviewapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
        val trendingMoviesList = device.waitAndFindObject(By.desc("trending_movies_list"), 100000)
        device.waitForIdle()
        trendingMoviesList.setGestureMargin(device.displayWidth / 5)

        repeat(10) {
            trendingMoviesList.scroll(Direction.RIGHT, 0.5f)
        }
    }
}
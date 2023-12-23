package com.tafreshiali.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * # Some Useful Resources For Learn Ui Testing For Benchmark :
 *
 * * ### [The definitive guide of Android UI Automator with Kotlin](https://proandroiddev.com/the-definitive-guide-of-android-ui-automator-with-kotlin-2eab40edab0d)
 * * ### [UI automation test in Jetpack Compose Using UI Automator](https://medium.com/@jeffemuveyan/ui-automation-test-in-jetpack-compose-using-uiautomator-5e29f7d33882)
 * * ### [UI Testing in Jetpack Compose](https://betterprogramming.pub/ui-testing-in-jetpack-compose-788d726db94e)
 * */
@RequiresApi(Build.VERSION_CODES.N)
@RunWith(AndroidJUnit4::class)
class MainScenarioBenchmark {

    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()


    @Test
    fun startupCompilationModeNone() = startup(mode = CompilationMode.None())

    @Test
    fun startupCompilationModePartial() = startup(mode = CompilationMode.Partial())

    @Test
    fun runAndMeasureTheMainScenarioCompilationModeNone() =
        runAndMeasureTheMainScenario(mode = CompilationMode.None())

    @Test
    fun runAndMeasureTheMainScenarioCompilationModePartial() =
        runAndMeasureTheMainScenario(mode = CompilationMode.Partial())


    fun startup(mode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.tafreshiali.moviewapp",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode
    )
    {
        pressHome()
        startActivityAndWait()
    }

    fun runAndMeasureTheMainScenario(mode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "com.tafreshiali.moviewapp",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = mode
    ) {
        pressHome()
        startActivityAndWait()
        acceptNotificationPermission()
        scrollTrendingMoviesList()
    }
}

fun MacrobenchmarkScope.acceptNotificationPermission() {
    val requestedElement = By.text("Allow")
    device.wait(Until.hasObject(requestedElement), 3000)
    val tvAllow = device.findObject(requestedElement)
    tvAllow?.let {
        it.click()
        device.wait(Until.gone(By.text("Allow")), 1000)
    }
}

fun MacrobenchmarkScope.scrollTrendingMoviesList() {
    device.waitForIdle()
    val trendingMoviesList = device.waitAndFindObject(By.desc("trending_movies_list"), 100000)
    trendingMoviesList.setGestureMargin(device.displayWidth / 5)

    repeat(10) {
        trendingMoviesList.scroll(Direction.RIGHT, 0.5f)
    }
    device.waitForIdle()
}
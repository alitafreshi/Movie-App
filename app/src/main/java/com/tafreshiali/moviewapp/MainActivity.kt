package com.tafreshiali.moviewapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.tafreshiali.moviewapp.databinding.FragmentContainerLayoutBinding
import com.tafreshiali.moviewapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    AndroidViewBinding(
                        factory = FragmentContainerLayoutBinding::inflate,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                LaunchedEffect(key1 = true) {
                    appViewModel.init()
                }
            }
        }
    }
}
package com.tafreshiali.moviewapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
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
            val clipboardManager = LocalClipboardManager.current
            LaunchedEffect(key1 = true) {
                appViewModel.init()
            }
            MovieAppTheme {
                val appState = appViewModel.viewState.collectAsState()
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.fillMaxSize().clickable {
                            clipboardManager.setText(AnnotatedString(appState.value.fcmToken))
                        },
                        text = appState.value.fcmToken,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
                    )
                    /* AndroidViewBinding(
                         factory = FragmentContainerLayoutBinding::inflate,
                         modifier = Modifier.fillMaxSize()
                     )*/
                }
            }
        }
    }
}
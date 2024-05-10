package com.tafreshiali.ui_kit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@ExperimentalMaterial3Api
@Composable
fun BaseScreenContainer(
    topAppBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    screenContent: @Composable (contentPaddingValues: PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = topAppBar,
        bottomBar = bottomBar
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}
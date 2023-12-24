package com.tafreshiali.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun BaseScreenContainer(
    topAppBarNavigationIcon: @Composable () -> Unit = {},
    topAppBarActions: @Composable RowScope.() -> Unit = {},
    screenContent: @Composable (contentPaddingValues: PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection).semantics {
            testTagsAsResourceId = true
        },
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {},
                navigationIcon = topAppBarNavigationIcon,
                actions = topAppBarActions
            )
        }
    ) { paddingValues ->
        screenContent(contentPaddingValues = paddingValues)
    }
}
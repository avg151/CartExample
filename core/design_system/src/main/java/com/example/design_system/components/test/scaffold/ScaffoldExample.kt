package com.example.design_system.components.test.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() //раскрывается при движении вниз с любого места
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior() //раскрывается при движении вниз только с началом списка

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
//            TopAppBar(
//            MediumTopAppBar(
            LargeTopAppBar(
                title = {
                    Text("Сворачиваемый заголовок")
                }, scrollBehavior = scrollBehavior
            )
        }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(paddingValues = innerPadding)) {
            items(count = 150) { item ->
                Text(
                    text = item.toString(), modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                )
            }
        }
    }
}

@Preview
@Composable
fun ScaffoldExamplePreview() {
    ScaffoldExample()
}
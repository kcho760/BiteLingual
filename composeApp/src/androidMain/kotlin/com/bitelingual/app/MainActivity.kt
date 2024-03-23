package com.bitelingual.app

import App
import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Using remember to hold the showCamera state
            var showCamera by remember { mutableStateOf(false) }

            MainScreen(
                onCaptureClick = {
                    // Toggle the camera visibility
                    showCamera = !showCamera
                },
                onPickFromGalleryClick = {
                    // Implement what happens when gallery is picked
                },
                showCamera = showCamera
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    // Provide default preview implementations or mock data for previews
    MainScreen(
        onCaptureClick = { /* Preview mock */ },
        onPickFromGalleryClick = { /* Preview mock */ },
        showCamera = false
    )
}
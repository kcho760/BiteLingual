package com.bitelingual.app

import App
import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // You will need to manage the actual value of showCamera with some state logic
        val showCamera = false // This should be dynamic based on your app's state

        setContent {
            MainScreen(
                onCaptureClick = {
                    // TODO: Implement what happens when capture is clicked
                },
                onPickFromGalleryClick = {
                    // TODO: Implement what happens when gallery is picked
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
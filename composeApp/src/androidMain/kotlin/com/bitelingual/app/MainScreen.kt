import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.camera.core.Preview
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun MainScreen(
    onCaptureClick: () -> Unit,
    onPickFromGalleryClick: () -> Unit,
    showCamera: Boolean // Add a flag to toggle camera preview
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showCamera) {
            CameraPreview() // Here is where CameraPreview is called
        } else {
            Text("Welcome to Bitelingual",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 24.dp))
            Button(
                onClick = { onCaptureClick() },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("Capture Food Image")
            }
            Button(onClick = { onPickFromGalleryClick() }) {
                Text("Pick From Gallery")
            }
        }
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(factory = { ctx ->
        PreviewView(ctx).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(surfaceProvider)
                }
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview)
                } catch (exc: Exception) {
                    Log.e("CameraPreview", "Use case binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(ctx))
        }
    }, Modifier.fillMaxSize())
}
@Composable
fun GalleryPicker(onImagePicked: (String) -> Unit) {
    Button(onClick = {
        // Invoke this with the path of the picked image from the gallery
        onImagePicked("path/to/gallery/image.jpg")
    }) {
        Text("Pick Image from Gallery")
    }
}

@Composable
fun FoodIdentificationAndTranslation(imagePath: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Image Path: $imagePath",
            style = MaterialTheme.typography.displayMedium)
        // Display additional information here as needed
    }
}

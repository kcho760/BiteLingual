import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun MainScreen(
    onCaptureClick: () -> Unit,
    onPickFromGalleryClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Bitelingual",
            style = MaterialTheme.typography.h4,
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

@Composable
fun CameraCapture(onImageCaptured: (String) -> Unit) {
    Button(onClick = {
        // Invoke this with the path of the captured image
        onImageCaptured("path/to/captured/image.jpg")
    }) {
        Text("Capture Image")
    }
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
            style = MaterialTheme.typography.h5)
        // Display additional information here as needed
    }
}

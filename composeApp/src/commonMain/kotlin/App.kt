import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Bienvenidos")
        Text("Curso Kotlin Multiplataforma con Compose")
    }
}
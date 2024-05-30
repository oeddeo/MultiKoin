import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    KoinInitializer().init()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeTest",
    ) {
        App()
    }
}
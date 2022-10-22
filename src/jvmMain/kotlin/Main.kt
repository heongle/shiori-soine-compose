import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ui.Soine

@Composable
@Preview
fun App(changeTitle: (String) -> Unit) {
    MaterialTheme {
        Soine(changeTitle = changeTitle)
    }
}

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(410.dp, 605.dp)
    )
    var onTop by remember { mutableStateOf(false) }
    var showing by remember { mutableStateOf(true) }
    Tray(
        icon = painterResource("icon.png"),
        onAction = {
            onTop = true
            state.isMinimized = false
        },
    )
    Window(
        icon = painterResource("icon.png"),
        title = Kaomoji.Awake,
        onCloseRequest = ::exitApplication,
        visible = showing,
        state = state,
        resizable = false,
        alwaysOnTop = onTop,
    ) {
        LaunchedEffect(state) {
            snapshotFlow { state.isMinimized }
                .onEach {
                    showing = !it
                    onTop = false
                }
                .launchIn(this)
        }
        App {
            window.title = it
        }
    }
}

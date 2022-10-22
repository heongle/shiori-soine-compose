package ui

import androidx.compose.runtime.*
import utility.SoinePlayer

@Composable
fun rememberSoineState() = remember { SoineState() }

class SoineState {
    private val soinePlayer = SoinePlayer("shiori_shinnon_l.wav", "shiori_shinnon_r.wav")
    var playing by mutableStateOf(false)
        private set

    fun toggleSoine() {
        playing = if(soinePlayer.looping) {
            soinePlayer.stop()
            false
        } else {
            soinePlayer.start()
            true
        }
    }

    fun getSoineImageName(): String {
        return if (playing) {
            "shiori_eye_close.jpg"
        } else {
            "shiori_eye_open.jpg"
        }
    }
}

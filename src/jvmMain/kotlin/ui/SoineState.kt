package ui

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utility.SoinePlayer

@Composable
fun rememberSoineState(coroutineScope: CoroutineScope = rememberCoroutineScope()) = remember {
    SoineState(coroutineScope)
}

class SoineState(private val coroutineScope: CoroutineScope) {
    private val soinePlayer = SoinePlayer("shiori_shinnon_l.wav", "shiori_shinnon_r.wav")
    private var loading = false
    var playing by mutableStateOf(false)
        private set

    fun toggleSoine() {
        if (!loading) {
            loading = true
            coroutineScope.launch(Dispatchers.IO) {
                playing = if(soinePlayer.looping) {
                    soinePlayer.stop()
                    false
                } else {
                    soinePlayer.start()
                    true
                }
                loading = false
            }
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

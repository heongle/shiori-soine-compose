package ui

import Kaomoji
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun Soine(soineState: SoineState = rememberSoineState(), changeTitle: (String) -> Unit) {
    LaunchedEffect(soineState.playing) {
        if(soineState.playing) {
            changeTitle(Kaomoji.Sleeping)
        } else {
            changeTitle(Kaomoji.Awake)
        }
    }

    Image(
        painter = painterResource(soineState.getSoineImageName()),
        contentDescription = "",
        modifier = Modifier.fillMaxSize().clickable(onClick = soineState::toggleSoine),
        contentScale = ContentScale.FillHeight
    )
}

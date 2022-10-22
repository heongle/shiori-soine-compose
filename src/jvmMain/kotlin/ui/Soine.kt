package ui

import Kaomoji
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Soine(soineState: SoineState = rememberSoineState(), changeTitle: (String) -> Unit) {
    Image(
        painter = painterResource(soineState.getSoineImageName()),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF6A596C).copy(alpha = 0.9f)
            ),
            onClick = soineState::toggleSoine
        ) {
            Text(
                fontSize = 24.sp,
                color = Color.White,
                text = if(soineState.playing) {
                    changeTitle(Kaomoji.Sleeping)
                    Kaomoji.Awake
                } else {
                    changeTitle(Kaomoji.Awake)
                    Kaomoji.Sleeping
                }
            )
        }
    }
}

package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokemonCardShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(
            x = translateAnim,
            y = translateAnim
        ),
        end = Offset(
            x = translateAnim + 200f,
            y = translateAnim + 200f
        )
    )

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(all = 12.dp),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(
                        width = 40.dp,
                        height = 20.dp
                    )
                    .align(alignment = Alignment.TopEnd)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .size(size = 100.dp)
                        .background(
                            brush = brush,
                            shape = RoundedCornerShape(size = 50.dp)
                        )
                )

                Spacer(modifier = Modifier.height(height = 12.dp))

                Spacer(
                    modifier = Modifier
                        .height(height = 20.dp)
                        .width(width = 80.dp)
                        .background(
                            brush = brush,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    }
}

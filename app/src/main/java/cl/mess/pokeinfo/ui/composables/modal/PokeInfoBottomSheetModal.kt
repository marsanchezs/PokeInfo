package cl.mess.pokeinfo.ui.composables.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PokeInfoBottomSheetModal(
    onDismiss: () -> Unit,
    horizontalPadding: Dp = 24.dp,
    dragHandle: (@Composable () -> Unit)? = { PokeInfoBottomSheetModalDragHandle() },
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onDismiss()
                }
        )

        Card(
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .imePadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    /* No-op: prevents closing */
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding)
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                dragHandle?.invoke()
                content()
            }
        }
    }
}

@Composable
fun PokeInfoBottomSheetModalDragHandle() {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(width = 40.dp)
            .height(height = 4.dp)
            .clip(shape = RoundedCornerShape(percent = 50))
            .background(color = Color.Black.copy(alpha = 0.5f))
    )
}

package cl.mess.pokeinfo.home.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModalFilterItem(
    onClick: () -> Unit,
    imageVector: ImageVector,
    isSelected: Boolean,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 40.dp)
            .padding(horizontal = 60.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription =  ""
        )

        Spacer(modifier = Modifier.width(width = 8.dp))

        Text(
            text = text,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        if (isSelected)
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription =  ""
            )
    }
}

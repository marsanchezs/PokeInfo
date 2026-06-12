package cl.mess.pokeinfo.detail.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cl.mess.pokeinfo.app.R
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonPager(
    pagerState: PagerState,
    splitPosition: Dp,
    imageSize: Dp,
    images: List<String>
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = splitPosition - imageSize / 2)
            .zIndex(zIndex = 1f)
    ) {

        HorizontalPager(
            state = pagerState,
            pageSpacing = 16.dp
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = images[page],
                    contentDescription = "",
                    modifier = Modifier
                        .size(size = imageSize)
                        .clip(shape = RoundedCornerShape(size = 16.dp))
                )
            }
        }

        Icon(
            modifier = Modifier
                .offset(y = 8.dp)
                .size(size = 40.dp)
                .align(alignment = Alignment.CenterStart)
                .padding(start = 16.dp)
                .clickable(
                    enabled = pagerState.currentPage > 0,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }
                    }
                ),
            painter = painterResource(id = R.drawable.ic_outline_arrow_back_ios_24),
            contentDescription = "",
            tint = if (pagerState.currentPage > 0)
                Color.Gray
            else
                Color.Gray.copy(alpha = 0f)
        )

        Icon(
            modifier = Modifier
                .offset(y = 8.dp)
                .size(size = 40.dp)
                .align(alignment = Alignment.CenterEnd)
                .padding(end = 16.dp)
                .clickable(
                    enabled = pagerState.currentPage < images.lastIndex,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                ),
            painter = painterResource(id = R.drawable.ic_outline_arrow_forward_ios_24),
            contentDescription = "",
            tint = if (pagerState.currentPage < images.lastIndex)
                Color.Gray
            else
                Color.Gray.copy(alpha = 0f)
        )

        PokemonPagerIndicator(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .offset(y = (-20).dp)
                .align(alignment = Alignment.BottomCenter)
                .padding(top = imageSize + 12.dp)
        )
    }
}

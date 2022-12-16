package ir.kaaveh.newsdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import ir.kaaveh.designsystem.component.FavoriteIcon
import ir.kaaveh.domain.model.News

@Composable
fun NewsDetailScreen(
    news: News?,
    viewModel: NewsDetailViewModel = hiltViewModel(),
) {

    val webviewState = rememberWebViewState(news?.url ?: "")
    var favorite by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        WebView(
            state = webviewState,
            modifier = Modifier.fillMaxSize(),
            captureBackPresses = false,
        )

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                viewModel.onFavoriteClick(news)
                favorite = !favorite
            },
            backgroundColor = Color.White,
        ) {
            FavoriteIcon(isFavorite = favorite) {}
        }
    }

}
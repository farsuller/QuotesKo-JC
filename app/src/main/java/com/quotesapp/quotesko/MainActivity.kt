package com.quotesapp.quotesko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import com.quotesapp.quotesko.presentation.ErrorScreen
import com.quotesapp.quotesko.presentation.LoadingScreen
import com.quotesapp.quotesko.presentation.MainViewModel
import com.quotesapp.quotesko.presentation.RandomQuoteScreen
import com.quotesapp.quotesko.service.QuotesNotificationService
import com.quotesapp.quotesko.ui.theme.QuotesKoJCTheme
import com.quotesapp.quotesko.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuotesKoJCTheme {
                val apiState = mainViewModel.response.value
                val service = QuotesNotificationService(this)
                val scope = rememberCoroutineScope()

                var randomIndex by remember { mutableIntStateOf(1) }
                var quotes by remember { mutableStateOf("") }
                var author by remember { mutableStateOf("") }

                LaunchedEffect(key1 = true, block = {
                    mainViewModel.getRandomQuotes()
                })

                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                text = "Quote",
                                fontFamily = FontFamily.Serif,
                            )
                        })
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            scope.launch {
                                randomIndex = Random.nextInt(0, 49)
                            }

                            // mainViewModel.showNotification(quote = quotes, author = author)
                            service.showExpandableNotification(quote = quotes, author = author)
                        }) {
                            Icon(imageVector = Icons.Rounded.Shuffle, contentDescription = null)
                        }
                    },
                ) { paddingValues ->

                    AnimatedContent(
                        targetState = apiState,
                        label = "animated_content",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        transitionSpec = {
                            fadeIn(
                                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                            ) togetherWith fadeOut(
                                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                            )
                        },
                    ) { result ->
                        when (result) {
                            ApiState.Loading -> {
                                LoadingScreen()
                            }

                            is ApiState.Success -> {
                                val quote = result.data.body()!!
                                RandomQuoteScreen(quote = quote, index = randomIndex)

                                quotes = quote[randomIndex].q
                                author = quote[randomIndex].a
                            }

                            is ApiState.Error -> {
                                ErrorScreen(error = result.error.message ?: "Something went wrong")
                            }

                            else -> Unit
                        }
                    }
                }
            }
        }
    }
}

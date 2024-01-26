package com.quotesapp.quotesko.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quotesapp.quotesko.model.Verse

@Composable
fun VerseScreen(verse: Verse) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            verse.verse?.details?.text?.let { v ->
                Text(
                    text = v,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
            Spacer(modifier = Modifier.size(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                verse.verse?.details?.reference?.let { ref ->
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = ref,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace,
                    )
                }

                verse.verse?.details?.version?.let { ref ->
                    Text(
                        text = ref,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace,
                    )
                }
            }
        }
    }
}

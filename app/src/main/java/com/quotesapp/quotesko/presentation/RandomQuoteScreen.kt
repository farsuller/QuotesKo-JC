package com.quotesapp.quotesko.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quotesapp.quotesko.model.QuotesV2

@Composable
fun RandomQuoteScreen(quote: QuotesV2, index:Int = 1){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
          imageVector = Icons.Rounded.FormatQuote,
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )

        Text(text = quote[index].q,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.size(8.dp))
        Box(modifier = Modifier.fillMaxWidth(.96F),){
            Text(text = quote[index].a,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace
            )
        }
    }
}
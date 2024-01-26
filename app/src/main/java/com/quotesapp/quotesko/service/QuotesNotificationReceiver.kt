package com.quotesapp.quotesko.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.quotesapp.quotesko.utils.QuoteNotification

class QuotesNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val service = QuotesNotificationService(context)
        service.showNotification(quote = QuoteNotification.quote, author = QuoteNotification.author)
    }
}

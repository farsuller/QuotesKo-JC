package com.quotesapp.quotesko.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.quotesapp.quotesko.MainActivity
import com.quotesapp.quotesko.R
import com.quotesapp.quotesko.utils.Constants.QUOTES_CHANNEL_ID

class QuotesNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(quote: String, author:String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setContentTitle(quote)
            .setContentText(author)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    fun showExpandableNotification(quote: String, author:String) {
        val image = BitmapFactory.decodeResource(context.resources, R.drawable.quotesko)


        val notification = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(image)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(image)
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            2,
            notification
        )
    }

}
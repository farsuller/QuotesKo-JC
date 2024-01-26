package com.quotesapp.quotesko.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
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
        val image = context.bitmapFromResource(R.drawable.quotesko)


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
    fun showExpandableLongText(quote: String, author:String) {
        val notification = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText("Very big text")
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            1,
            notification
        )
    }

    fun showInboxStyleNotification(quote: String, author:String) {
        val notification = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
                    .addLine("Line 3")
                    .addLine("Line 4")
                    .addLine("Line 5")
                    .addLine("Line 6")
                    .addLine("Line 7")
            )
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            1,
            notification
        )
    }

    fun showNotificationGroup(quote: String, author:String) {
        val groupId = "quotes_group"
        val summaryId = 0

        val notification1 = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()

        val notification2 = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .addLine("Line 1")
                    .addLine("Line 2")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .build()

        val summaryNotification = NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
            .setContentTitle(quote)
            .setContentText(author)
            .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat
                    .InboxStyle()
                    .setSummaryText("Quotes reminders missed")
                    .setBigContentTitle("Quotes Reminders")
            )
            .setAutoCancel(true)
            .setGroup(groupId)
            .setGroupSummary(true)
            .build()

        notificationManager.notify(
            2,
            notification1
        )
        notificationManager.notify(
            1,
            notification2
        )
        notificationManager.notify(
            2,
            summaryNotification
        )
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}
package com.quotesapp.quotesko.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import com.quotesapp.quotesko.MainActivity
import com.quotesapp.quotesko.R
import com.quotesapp.quotesko.utils.Constants.QUOTES_CHANNEL_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {


    @Singleton
    @Provides
    fun provideNotificationBuilder(@ApplicationContext context: Context) : NotificationCompat.Builder{
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context,QUOTES_CHANNEL_ID)
            .setContentTitle("Inject Notification")
            .setContentText("Inject Content")
            .setSmallIcon(R.drawable.quotesko_icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(activityPendingIntent)
            .setVisibility(VISIBILITY_PUBLIC)
//            .setVisibility(VISIBILITY_SECRET) This scenario if you want your notification to be displayed once unlocked
//            .setVisibility(VISIBILITY_PRIVATE)
//            .setPublicVersion(
//                NotificationCompat.Builder(context, QUOTES_CHANNEL_ID)
//                    .setContentTitle("Hidden")
//                    .setContentText("Unlock to see the message")
//                    .build()
//            ) This commented are in the scenario of displaying notification on lock screen but the content are private

    }

    @Singleton
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManagerCompat{
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                QUOTES_CHANNEL_ID,
                "Quotes",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }
}
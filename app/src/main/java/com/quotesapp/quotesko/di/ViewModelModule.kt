package com.quotesapp.quotesko.di

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.experiment.data.RemoteDataRepositoryImpl
import com.quotesapp.quotesko.presentation.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModule(
        remoteDataRepository: RemoteDataRepositoryImpl,
        notificationBuilder: NotificationCompat.Builder,
        notificationManager: NotificationManagerCompat,
    ): MainViewModel {
        return MainViewModel(remoteDataRepository, notificationBuilder, notificationManager)
    }
}

package com.tafreshiali.firebase.fcm.di

import com.tafreshiali.firebase.fcm.utils.NotificationUtils
import com.tafreshiali.firebase.fcm.utils.NotificationUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class FcmModule {

    @Binds
    abstract fun bindNotificationUtilsImpl(notificationUtilsImpl: NotificationUtilsImpl): NotificationUtils
}
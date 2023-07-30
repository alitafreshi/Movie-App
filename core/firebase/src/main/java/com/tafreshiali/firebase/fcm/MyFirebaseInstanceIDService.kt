package com.tafreshiali.firebase.fcm

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ServiceLifecycleDispatcher
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.tafreshiali.firebase.fcm.model.NotificationMessage
import com.tafreshiali.firebase.fcm.model.toNotificationMessage
import com.tafreshiali.firebase.fcm.utils.NotificationUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseInstanceIDService : FirebaseMessagingService(), LifecycleOwner {

    private val mDispatcher = ServiceLifecycleDispatcher(this)

    @Inject
    lateinit var notificationUtils: NotificationUtils
    @CallSuper
    override fun onCreate() {
        mDispatcher.onServicePreSuperOnCreate()
        super.onCreate()
    }

    @CallSuper
    override fun onDestroy() {
        mDispatcher.onServicePreSuperOnDestroy()
        super.onDestroy()
    }
    override val lifecycle: Lifecycle
        get() = mDispatcher.lifecycle

    override fun onNewToken(token: String) {
        Log.d("FCM", "newToken is : $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", "onMessageReceived: ${remoteMessage.data} ")

        val notificationMessage = remoteMessage.data.toNotificationMessage()
        notificationUtils.createNotification(notificationMessage)

    }
}
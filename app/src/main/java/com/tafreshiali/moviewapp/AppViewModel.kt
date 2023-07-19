package com.tafreshiali.moviewapp

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tafreshiali.components.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : BaseViewModel<Unit, AppEvents, Nothing>() {
    fun init() {
        onTriggerEvent(event = AppEvents.InitAppFcm)
    }

    override fun onTriggerEvent(event: AppEvents) {
        when (event) {
            is AppEvents.InitAppFcm -> {
                initFireBaseCloudMessaging()
            }
        }
    }


    override fun initNewViewState() {}

    private fun initFireBaseCloudMessaging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.d("FCM", "the fcm token is : $token")
        })
    }
}
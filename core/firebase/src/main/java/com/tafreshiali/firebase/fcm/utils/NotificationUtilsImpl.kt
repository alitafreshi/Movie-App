package com.tafreshiali.firebase.fcm.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.media.RingtoneManager
import android.os.Build
import android.text.Layout
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AlignmentSpan
import android.text.style.LocaleSpan
import android.text.style.StyleSpan
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.tafreshiali.firebase.R
import com.tafreshiali.firebase.fcm.model.NotificationMessage
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_CALL
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_GENERAL_MESSAGE
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_CAFE_BAZAAR
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_GOOGLE_PLAY
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_INSTAGRAM
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_IRANAPPS
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_LINK
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_MARKET
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_MYKET
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_PAGE
import com.tafreshiali.firebase.fcm.utils.NotificationType.ACTION_OPEN_TELEGRAM
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import java.util.Random
import javax.inject.Inject


interface NotificationUtils {
    fun createNotification(notificationMessage: NotificationMessage)
}


class NotificationUtilsImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NotificationUtils {


    override fun createNotification(
        notificationMessage: NotificationMessage
    ) {
        try {
            val random = Random(System.nanoTime())
            val notificationId = random.nextInt()
            val notificationManager = NotificationManagerCompat.from(context)
            val notifMessage = notificationMessage.copy(notificationId = notificationId)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel724 = NotificationChannel(
                    getChannelId(notifMessage.action).toString(),
                    "724",
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel724.apply {
                    enableVibration(true)
                    enableLights(true)
                    setBypassDnd(true)
                    importance = NotificationManager.IMPORTANCE_HIGH
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                }


                notificationManager.createNotificationChannel(channel724)
                notificationManager.notify(
                    notificationId,
                    buildNotification(
                        context,
                        channel724,
                        notifMessage
                    ).build()
                )

            } else notificationManager.notify(
                notificationId,
                buildNotification(context, null, notifMessage).build()
            )
        } catch (exc: Exception) {

        }
    }

    private fun getChannelId(notificationMessage: String): Int {
        return when (notificationMessage) {
            ACTION_CALL -> 7240
            ACTION_OPEN_LINK -> 7241
            ACTION_OPEN_MARKET -> 7243
            ACTION_GENERAL_MESSAGE -> 7244
            ACTION_OPEN_PAGE -> 7245
            ACTION_OPEN_TELEGRAM -> 7246
            ACTION_OPEN_CAFE_BAZAAR -> 7247
            ACTION_OPEN_IRANAPPS -> 7248
            ACTION_OPEN_MYKET -> 7249
            ACTION_OPEN_GOOGLE_PLAY -> 72410
            ACTION_OPEN_INSTAGRAM -> 72411
            else -> 7244
        }
    }

    private fun buildNotification(
        context: Context,
        channel: NotificationChannel?,
        notificationMessage: NotificationMessage
    ): NotificationCompat.Builder {
        val builder =
            NotificationCompat.Builder(
                context,
                getChannelId(notificationMessage.action).toString()
            )
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val rtlTitle = SpannableStringBuilder(notificationMessage.title)
        val rtlDescription = SpannableStringBuilder(notificationMessage.description)


        rtlTitle.setSpan(
            LocaleSpan(Locale("fa")),
            0,
            rtlTitle.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        rtlTitle.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL),
            0,
            rtlTitle.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        rtlTitle.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            rtlTitle.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        rtlDescription.setSpan(
            LocaleSpan(Locale("fa")),
            0,
            rtlDescription.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        rtlDescription.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL),
            0,
            rtlDescription.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        rtlDescription.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            rtlDescription.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        builder
            .setContentTitle(rtlTitle)
            .setContentText(rtlDescription)
            .setSmallIcon(R.drawable.ic_movie_notif)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_movie_notif
                )
            )
            .setAutoCancel(true)
            .setSound(uri)
            .setVibrate(longArrayOf(724, 724, 724))
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        if (Build.VERSION.SDK_INT <= 25) {
            builder.priority = Notification.PRIORITY_MAX
        } else {
            builder.priority = NotificationManager.IMPORTANCE_MAX
        }
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(channel!!.id)
        }
        return builder
    }

}
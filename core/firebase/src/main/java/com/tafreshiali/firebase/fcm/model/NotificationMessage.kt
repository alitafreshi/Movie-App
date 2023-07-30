package com.tafreshiali.firebase.fcm.model

import com.tafreshiali.firebase.fcm.constance.NotificationConstance.ACTION
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.BUTTON_ACTION_TITLE
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.DESC
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.EXPIRE_TIME
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.ID
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.IMAGE_URL
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.MAX_VERSION
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.MIN_VERSION
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.TARGET
import com.tafreshiali.firebase.fcm.constance.NotificationConstance.TITLE
import com.tafreshiali.firebase.fcm.utils.NotificationType
import com.tafreshiali.firebase.fcm.utils.extractValue
import com.tafreshiali.firebase.fcm.utils.getRandomId

data class NotificationMessage(
    val id: String,
    val title: String,
    val description: String,
    val action: String,
    val target: String,
    val minVersionCode: Int,
    val maxVersionCode: Int,
    val expireTime: String,
    val imageUrl: String,
    val buttonActionTitles: String,
    val notificationId: Int = 0
)

fun Map<String, String>.toNotificationMessage(): NotificationMessage = NotificationMessage(
    id = (extractValue(this, ID, getRandomId())).toString(),
    title = (extractValue(this, TITLE, "NA")),
    description = (extractValue(this, DESC, "NA")),
    action = (extractValue(this, ACTION, NotificationType.ACTION_GENERAL_MESSAGE)),
    target = (extractValue(this, TARGET, NotificationType.TARGET_APP)),
    minVersionCode = (extractValue(this, MIN_VERSION, 400)),
    maxVersionCode = (extractValue(this, MAX_VERSION, 900)),
    expireTime = (extractValue(this, EXPIRE_TIME, "2025-01-01")),
    buttonActionTitles = (extractValue(this, BUTTON_ACTION_TITLE, "")),
    imageUrl = (extractValue(this, IMAGE_URL, ""))
)

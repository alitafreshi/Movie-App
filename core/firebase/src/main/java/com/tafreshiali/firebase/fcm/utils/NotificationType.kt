package com.tafreshiali.firebase.fcm.utils

object NotificationType {

    const val ACTION_OPEN_TELEGRAM = "open_telegram"
    const val ACTION_OPEN_INSTAGRAM = "open_instagram"
    const val ACTION_OPEN_MARKET = "open_market"
    const val ACTION_OPEN_CAFE_BAZAAR = "open_cafe_bazaar"
    const val ACTION_OPEN_IRANAPPS = "open_iranapps"
    const val ACTION_OPEN_MYKET = "open_myket"
    const val ACTION_OPEN_GOOGLE_PLAY = "open_google_play"
    const val ACTION_OPEN_PAGE = "open_page"
    const val ACTION_OPEN_LINK = "open_link"
    const val ACTION_CALL = "call"
    const val ACTION_GENERAL_MESSAGE = "general_message"

    /**
     * when notif message action is ACTION_OPEN_PAGE, the following targets determine the destination
     */

    const val TARGET_APP = "app"

}
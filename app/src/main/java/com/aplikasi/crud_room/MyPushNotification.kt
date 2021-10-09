package com.aplikasi.crud_room

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyPushNotification : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}
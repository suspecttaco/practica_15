package com.simposio.practica15

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", "Nuevo token generado: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.let {
            Log.d("FCM_MESSAGE", "Cuerpo de la notificacion: ${it.body}")
            showNotification(this, "canal_practica_15", it.title ?: "Alerta", it.body ?: "")
        }

        if (message.data.isNotEmpty()) {
            val specialTitle = message.data["titulo_oculto"] ?: "Mensaje Oculto"
            val specialMessage = message.data["mensaje_oculto"] ?: "Tienes datos nuevos"

            showNotification(this, "canal_practica_15", specialTitle, specialMessage)
        }
    }
}
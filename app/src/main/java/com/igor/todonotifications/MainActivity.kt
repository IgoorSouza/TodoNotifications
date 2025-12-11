package com.igor.todonotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.igor.todonotifications.notifications.NotificationHelper
import com.igor.todonotifications.ui.TodoApp
import com.igor.todonotifications.ui.theme.TodoNotificationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        NotificationHelper.createNotificationChannel(this)

        setContent {
            TodoNotificationsTheme {
                TodoApp()
            }
        }
    }
}
package com.igor.todonotifications.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.work.*
import com.igor.todonotifications.notifications.NotificationWorker
import java.util.*
import java.util.concurrent.TimeUnit

object TaskRepository {
    private val _tasks = mutableStateListOf<Task>()

    val tasks: List<Task>
        get() = _tasks

    fun addTask(context: Context, title: String, description: String = "", scheduledTime: String = "") {
        if (title.isNotBlank()) {
            val newTask = Task(title = title, description = description, scheduledTime = scheduledTime)
            _tasks.add(0, newTask)
            scheduleNotification(context, newTask)
        }
    }

    fun removeTaskById(context: Context, taskId: String): Boolean {
        cancelNotification(context, taskId)
        return _tasks.removeIf { it.id == taskId }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.find { it.id == taskId }
    }

    private fun calculateInitialDelay(scheduledTime: String): Long? {
        if (scheduledTime.isBlank()) return null

        return try {
            val parts = scheduledTime.split(":")
            if (parts.size != 2) return null

            val scheduledHour = parts[0].toInt()
            val scheduledMinute = parts[1].toInt()

            val now = Calendar.getInstance()
            val scheduledTimeCal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, scheduledHour)
                set(Calendar.MINUTE, scheduledMinute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            if (scheduledTimeCal.before(now)) {
                scheduledTimeCal.add(Calendar.DAY_OF_MONTH, 1)
            }

            scheduledTimeCal.timeInMillis - now.timeInMillis

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun scheduleNotification(context: Context, task: Task) {
        if (task.scheduledTime.isBlank()) {
            cancelNotification(context, task.id)
            return
        }

        val initialDelayMillis = calculateInitialDelay(task.scheduledTime)
            ?: return

        val inputData = Data.Builder()
            .putString(NotificationWorker.TASK_ID_KEY, task.id)
            .putString(NotificationWorker.TASK_TITLE_KEY, task.title)
            .putString(NotificationWorker.TASK_DESCRIPTION_KEY, task.description)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(initialDelayMillis, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .setConstraints(constraints)
            .addTag(task.id)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            task.id,
            ExistingWorkPolicy.REPLACE,
            notificationWorkRequest
        )
    }

    fun cancelNotification(context: Context, taskId: String) {
        WorkManager.getInstance(context).cancelUniqueWork(taskId)
    }
}
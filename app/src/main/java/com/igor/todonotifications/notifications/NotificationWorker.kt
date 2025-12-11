package com.igor.todonotifications.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val TASK_ID_KEY = "taskId"
        const val TASK_TITLE_KEY = "taskTitle"
        const val TASK_DESCRIPTION_KEY = "taskDescription"
    }

    override suspend fun doWork(): Result {
        val taskId = inputData.getString(TASK_ID_KEY) ?: return Result.failure()
        val title = inputData.getString(TASK_TITLE_KEY) ?: return Result.failure()
        val description = inputData.getString(TASK_DESCRIPTION_KEY) ?: ""

        NotificationHelper.showNotification(
            applicationContext,
            taskId,
            title,
            description
        )

        return Result.success()
    }
}
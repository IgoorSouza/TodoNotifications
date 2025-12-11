package com.igor.todonotifications.data

import androidx.compose.runtime.mutableStateListOf

object TaskRepository {
    private val _tasks = mutableStateListOf(
        Task(
            title = "Desenvolver uma Lista de Tarefas",
            description = "Utilizar Jetpack Compose para construir uma Lista de Tarefas moderna e interativa.",
            scheduledTime = "10:00",
            isCompleted = false
        ),
        Task(
            title = "Lembrete: Beber Água",
            description = "Manter-se hidratado. Lembrete recorrente.",
            scheduledTime = "14:30",
            isCompleted = false
        ),
    )

    val tasks: List<Task>
        get() = _tasks

    fun addTask(title: String, description: String = "", scheduledTime: String = "") {
        if (title.isNotBlank()) {
            _tasks.add(0, Task(title = title, description = description, scheduledTime = scheduledTime))
        }
    }

    fun toggleTaskCompletion(task: Task) {
        val index = _tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(isCompleted = !task.isCompleted)
        }
    }

    fun removeTaskById(taskId: String): Boolean {
        return _tasks.removeIf { it.id == taskId }
    }

    fun getTaskById(taskId: String): Task? {
        return _tasks.find { it.id == taskId }
    }
}
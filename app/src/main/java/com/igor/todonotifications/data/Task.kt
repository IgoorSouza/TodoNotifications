package com.igor.todonotifications.data

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val scheduledTime: String,
    val description: String = "Nenhuma descrição fornecida.",
    val isCompleted: Boolean = false
)
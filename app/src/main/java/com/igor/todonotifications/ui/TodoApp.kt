package com.igor.todonotifications.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import com.igor.todonotifications.ui.theme.TodoNotificationsTheme

sealed class Screen {
    object List : Screen()
    object Add : Screen()
    data class Detail(val taskId: String) : Screen()
}

@Composable
fun TodoApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }

    val systemDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by rememberSaveable { mutableStateOf(systemDarkTheme) }

    TodoNotificationsTheme(darkTheme = isDarkTheme) {
        when (val screen = currentScreen) {
            Screen.List -> {
                TaskListScreen(
                    onTaskClick = { taskId -> currentScreen = Screen.Detail(taskId) },
                    onAddTaskClick = { currentScreen = Screen.Add },
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { isDarkTheme = !isDarkTheme }
                )
            }
            Screen.Add -> {
                AddTaskScreen(
                    onBackClick = { currentScreen = Screen.List },
                    isDarkTheme = isDarkTheme
                )
            }
            is Screen.Detail -> {
                TaskDetailScreen(
                    taskId = screen.taskId,
                    onBackClick = { currentScreen = Screen.List },
                    isDarkTheme = isDarkTheme
                )
            }
        }
    }
}
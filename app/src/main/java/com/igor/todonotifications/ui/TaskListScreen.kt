package com.igor.todonotifications.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.todonotifications.data.Task
import com.igor.todonotifications.data.TaskRepository
import com.igor.todonotifications.ui.theme.AppColors

@Composable
fun TaskListScreen(
    onTaskClick: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val tasks = TaskRepository.tasks
    val primaryColor = AppColors.primary(isDarkTheme)
    val textDarkColor = AppColors.textDark(isDarkTheme)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(AppColors.backgroundStart(isDarkTheme), AppColors.backgroundEnd(isDarkTheme))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Minhas Tarefas",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDarkColor,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = onToggleTheme) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Default.DarkMode,
                        contentDescription = "Alternar Tema",
                        tint = primaryColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhuma tarefa registrada.",
                        color = AppColors.textSecondary(isDarkTheme),
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(tasks, key = { it.id }) { task ->
                        TaskCard(task, onTaskClick, isDarkTheme)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddTaskClick,
            containerColor = primaryColor,
            shape = RoundedCornerShape(percent = 50),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 56.dp)
        ) {
            Icon(Icons.Filled.Add, "Adicionar Tarefa", tint = Color.White)
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onTaskClick: (String) -> Unit,
    isDarkTheme: Boolean
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    val primaryColor = AppColors.primary(isDarkTheme)
    val textDarkColor = AppColors.textDark(isDarkTheme)
    val cardBackgroundColor = AppColors.cardBackground(isDarkTheme)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTaskClick(task.id) }
                    .padding(end = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = { TaskRepository.toggleTaskCompletion(task) },
                        colors = CheckboxDefaults.colors(checkedColor = primaryColor)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (task.isCompleted) AppColors.textSecondary(isDarkTheme) else textDarkColor,
                        style = if (task.isCompleted) MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.LineThrough
                        ) else MaterialTheme.typography.bodyLarge
                    )
                }

                if (task.scheduledTime.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Horário: ${task.scheduledTime}",
                        fontSize = 12.sp,
                        color = AppColors.textSecondary(isDarkTheme),
                        modifier = Modifier.padding(start = 40.dp)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir Tarefa",
                        tint = Color.Red
                    )
                }

                IconButton(onClick = { onTaskClick(task.id) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Ver detalhes",
                        tint = primaryColor
                    )
                }
            }
        }
    }

    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            taskTitle = task.title,
            onConfirm = {
                TaskRepository.removeTaskById(task.id)
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false },
            isDarkTheme = isDarkTheme
        )
    }
}
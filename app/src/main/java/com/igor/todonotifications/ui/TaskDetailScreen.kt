package com.igor.todonotifications.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.todonotifications.data.TaskRepository
import com.igor.todonotifications.ui.theme.AppColors

@Composable
fun TaskDetailScreen(
    taskId: String,
    onBackClick: () -> Unit,
    isDarkTheme: Boolean
) {
    val task = TaskRepository.getTaskById(taskId)
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (task == null) {
        onBackClick()
        return
    }

    val primaryColor = AppColors.primary(isDarkTheme)
    val textDarkColor = AppColors.textDark(isDarkTheme)
    val dividerColor = AppColors.divider(isDarkTheme)
    val cardBackgroundColor = AppColors.cardBackground(isDarkTheme)

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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = primaryColor
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Voltar",
                        color = primaryColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir Tarefa",
                        tint = Color.Red
                    )
                }
            }

            Text(
                text = "Detalhes da Tarefa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textDarkColor,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = task.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDarkColor,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    HorizontalDivider(color = dividerColor)
                    Spacer(modifier = Modifier.height(16.dp))

                    DetailItem(
                        label = "Status",
                        value = if (task.isCompleted) "Concluída" else "Pendente",
                        valueColor = if (task.isCompleted) Color(0xFF388E3C) else Color.Red,
                        isDarkTheme = isDarkTheme
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        label = "Horário Programado",
                        value = task.scheduledTime.ifBlank { "Não programado" },
                        valueColor = primaryColor,
                        isDarkTheme = isDarkTheme
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        label = "Descrição",
                        value = task.description,
                        maxLines = Int.MAX_VALUE,
                        isDarkTheme = isDarkTheme
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
                onBackClick()
            },
            onDismiss = { showDeleteDialog = false },
            isDarkTheme = isDarkTheme
        )
    }
}

@Composable
fun DetailItem(
    label: String,
    value: String,
    valueColor: Color = Color.Unspecified,
    maxLines: Int = 1,
    isDarkTheme: Boolean
) {
    val textDarkColor = AppColors.textDark(isDarkTheme)
    val textSecondaryColor = AppColors.textSecondary(isDarkTheme)
    val finalValueColor = if (valueColor == Color.Unspecified) textDarkColor else valueColor

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = textSecondaryColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = finalValueColor,
            maxLines = maxLines
        )
    }
}
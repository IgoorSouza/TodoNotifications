package com.igor.todonotifications.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igor.todonotifications.data.TaskRepository
import com.igor.todonotifications.ui.theme.AppColors

@Composable
fun AddTaskScreen(
    onBackClick: () -> Unit,
    isDarkTheme: Boolean
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var scheduledTime by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val primaryColor = AppColors.primary(isDarkTheme)
    val textDarkColor = AppColors.textDark(isDarkTheme)
    val cardBackgroundColor = AppColors.cardBackground(isDarkTheme)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(AppColors.backgroundStart(isDarkTheme), AppColors.backgroundEnd(isDarkTheme))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onBackClick() }
                    .padding(bottom = 16.dp)
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

            Text(
                text = "Adicionar Nova Tarefa",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textDarkColor,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(8.dp, RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Título da Tarefa") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = textDarkColor,
                            unfocusedTextColor = textDarkColor,
                            focusedLabelColor = primaryColor,
                            unfocusedLabelColor = textDarkColor,
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = textDarkColor.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        value = scheduledTime,
                        onValueChange = { scheduledTime = it },
                        label = { Text("Horário Programado (HH:MM)") },
                        singleLine = true,
                        placeholder = { Text("Ex: 10:00") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = textDarkColor,
                            unfocusedTextColor = textDarkColor,
                            focusedLabelColor = primaryColor,
                            unfocusedLabelColor = textDarkColor,
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = textDarkColor.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Descrição (Opcional)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                            .padding(bottom = 20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = textDarkColor,
                            unfocusedTextColor = textDarkColor,
                            focusedLabelColor = primaryColor,
                            unfocusedLabelColor = textDarkColor,
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = textDarkColor.copy(alpha = 0.5f)
                        )
                    )

                    Button(
                        onClick = {
                            if (title.isNotBlank() && scheduledTime.isNotBlank()) {
                                isLoading = true
                                TaskRepository.addTask(
                                    title.trim(),
                                    description.trim(),
                                    scheduledTime.trim()
                                )
                                isLoading = false
                                onBackClick()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        if (isLoading)
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp
                            )
                        else
                            Text("Salvar Tarefa", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
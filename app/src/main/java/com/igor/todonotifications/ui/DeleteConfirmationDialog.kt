package com.igor.todonotifications.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.igor.todonotifications.ui.theme.AppColors
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun DeleteConfirmationDialog(
    taskTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val primaryColor = AppColors.primary(isDarkTheme)
    val textDarkColor = AppColors.textDark(isDarkTheme)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Confirmar Exclusão",
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        },
        text = {
            Text(
                "Tem certeza que deseja excluir a tarefa \"$taskTitle\"? Esta ação não pode ser desfeita.",
                color = textDarkColor
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
            ) {
                Text("Excluir", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(contentColor = primaryColor)
            ) {
                Text("Cancelar")
            }
        }
    )
}
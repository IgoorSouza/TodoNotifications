# Aplicativo de Lista de Tarefas com Notificações (Jetpack Compose)

Este projeto consiste em desenvolver uma **lista de tarefas** utilizando **Jetpack Compose** para a interface do usuário. O foco está em criar uma **interface moderna e responsiva**, com **boas práticas de UI/UX** e um fluxo de navegação simples para gerenciamento de atividades, incluindo a funcionalidade de **agendamento de tarefas e envio de notificações** usando WorkManager.  
Os dados são persistidos em memória (RAM) enquanto o aplicativo está em execução.

---

## 💻 Instruções de Execução

1. **Clone o repositório** ou abra o projeto no Android Studio.  
2. Certifique-se de que todas as dependências do **Gradle** estão sincronizadas.  
3. Execute o aplicativo em um **emulador ou dispositivo Android (SDK 24+)**.

### ⚠️ Permissão de Notificação
No **Android 13 (Tiramisu)** ou superior, é necessário conceder a permissão `POST_NOTIFICATIONS` para que os lembretes funcionem corretamente.

---

## 📱 Features

### **Gerenciamento de Tarefas e Notificações**
- **Listagem de Tarefas** com título e horário programado.  
- **Adição de Novas Tarefas** em uma tela de formulário dedicada (título, descrição e horário).  
- **Agendamento de Notificações** usando **WorkManager** para lembrar o usuário no horário escolhido.  
- **Exclusão de Tarefas** com modal de confirmação, que também **cancela a notificação** correspondente.  
- **Visualização de Detalhes** de cada tarefa, incluindo horário programado.

### **Navegação e Estado**
- Navegação entre três telas principais:  
  **Lista**, **Adicionar**, **Detalhes** (Single-Activity pattern).  
- Gerenciamento de estado em memória via `TaskRepository`.

### **UI/UX – Jetpack Compose + Material 3**
- Interface desenvolvida 100% em **Jetpack Compose**.  
- Uso do tema **Material 3** com estilização consistente.  
- **Gradiente de fundo** em todas as telas.  
- Suporte completo a **Dark Theme (Tema Escuro)**.  
- Uso de **Floating Action Button (FAB)** para criar novas tarefas.  
- Cards estilizados e paleta de cores coerente.

<img src="https://github.com/user-attachments/assets/9ee51644-a94e-4a60-8963-848dd24bad56" alt="Tela Inicial - Modo Escuro" width="300"/>  
<img src="https://github.com/user-attachments/assets/537929ff-1bd6-4de2-a37b-41199ef344bc" alt="Tela Inicial - Modo Claro" width="300"/>  
<img src="https://github.com/user-attachments/assets/d39f8909-5f18-4d4b-b55d-49f80236dc32" alt="Tela de Adição de Tarefa - Modo Escuro" width="300"/>
<img src="https://github.com/user-attachments/assets/0b8a1a8b-6595-41e5-bb18-142c5650de2c" alt="Tela de Adição de Tarefa - Modo Claro" width="300"/>
<img src="https://github.com/user-attachments/assets/3ee50b5c-ab7d-4d89-a347-f94bcb5c4a29" alt="Tela de Detalhes da Tarefa - Modo Escuro" width="300"/>
<img src="https://github.com/user-attachments/assets/6e8f105f-dc14-4091-b9b2-9b2d97c3c57a" alt="Tela de Detalhes da Tarefa - Modo Claro" width="300"/>

---

## 🛠️ Technologies Used

- **Language:** Kotlin  
- **Framework:** Jetpack Compose (Material 3)  
- **Data Persistence:** Memória (`mutableStateListOf` em `TaskRepository`)  
- **Agendamento:** WorkManager (para notificações agendadas persistentes)  
- **IDE:** Android Studio  
- **Build System:** Gradle  

---

## 🚀 Highlights

- Totalmente desenvolvido com **Jetpack Compose**.  
- **Notificações Agendadas** usando WorkManager, com cancelamento automático ao excluir tarefas.  
- Design moderno e consistente seguindo o **Material Design 3**.  
- Suporte a **Dark Theme** com cores adaptadas.  
- Implementação de navegação via `sealed class Screen`.  
- Lista eficiente utilizando `LazyColumn`.  
- Diálogos elegantes de confirmação de exclusão (`AlertDialog`).

---

package br.com.luk.toDoApp.config.dto

data class TaskRequestDTO (
    val title: String,
    val description: String,
    val completed: Boolean
)
package br.com.luk.toDoApp.service

import br.com.luk.toDoApp.config.dto.TaskRequestDTO
import br.com.luk.toDoApp.model.Tasks
import org.springframework.stereotype.Service
import br.com.luk.toDoApp.repository.*
import java.util.*

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun getAllTasks(): List<Tasks> = taskRepository.findAll()

    fun getTaskById(id: UUID): Tasks =
        taskRepository.findById(id).orElseThrow { Exception("Task not found") }

    fun createTask(taskDto: TaskRequestDTO): Tasks {
        val task = Tasks(
            id = UUID.randomUUID(),
            title = taskDto.title,
            description = taskDto.description,
            completed = taskDto.completed
        )
        return taskRepository.save(task)
    }

    fun updateTask(id: UUID, updatedTask: Tasks): Tasks {
        val existingTask = getTaskById(id)
        existingTask.title = updatedTask.title
        existingTask.description = updatedTask.description
        existingTask.completed = updatedTask.completed
        return taskRepository.save(existingTask)
    }

    fun markTaskAsComplete(id: UUID): Tasks? {
        val task = taskRepository.findById(id).orElse(null) ?: return null
        task.completed = !task.completed
        return taskRepository.save(task)
    }

    fun deleteTask(id: UUID): Boolean {
        return if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
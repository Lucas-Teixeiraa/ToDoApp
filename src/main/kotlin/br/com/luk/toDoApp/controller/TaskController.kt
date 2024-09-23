package br.com.luk.toDoApp.controller

import br.com.luk.toDoApp.config.dto.TaskRequestDTO
import br.com.luk.toDoApp.model.Tasks
import br.com.luk.toDoApp.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping
    fun getAllTasks(): List<Tasks> = taskService.getAllTasks()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: UUID): ResponseEntity<Tasks> {
        val task = taskService.getTaskById(id)
        return if (task != null) {
            ResponseEntity(task, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createTask(@RequestBody taskRequest: TaskRequestDTO): ResponseEntity<Tasks> {
        // Chama o serviço passando o TaskRequestDTO
        val createdTask = taskService.createTask(taskRequest)
        return ResponseEntity(createdTask, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: UUID, @RequestBody task: Tasks): ResponseEntity<Tasks> {
        val updatedTask = taskService.updateTask(id, task)
        return if (updatedTask != null) {
            ResponseEntity(updatedTask, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}/complete")
    fun markTaskAsComplete(@PathVariable id: UUID): ResponseEntity<Tasks> {
        val updatedTask = taskService.markTaskAsComplete(id)
        return if (updatedTask != null) {
            ResponseEntity(updatedTask, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: UUID): ResponseEntity<Void> {
        return if (taskService.deleteTask(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
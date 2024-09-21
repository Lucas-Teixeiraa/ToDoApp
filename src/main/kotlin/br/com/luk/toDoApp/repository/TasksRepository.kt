package br.com.luk.toDoApp.repository


import br.com.luk.toDoApp.model.Tasks
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : JpaRepository<Tasks, UUID>
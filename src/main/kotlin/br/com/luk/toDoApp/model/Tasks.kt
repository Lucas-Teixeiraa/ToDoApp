package br.com.luk.toDoApp.model


import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tasks")
data class Tasks(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var completed: Boolean = false
)

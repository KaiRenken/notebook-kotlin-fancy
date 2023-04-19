package de.neusta.notebookkotlinfancy.infrastructure.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "note")
class NoteEntity(

    @Id
    val id: UUID,

    val creationDate: LocalDateTime,

    val content: String,
)
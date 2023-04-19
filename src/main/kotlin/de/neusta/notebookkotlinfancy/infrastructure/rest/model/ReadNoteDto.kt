package de.neusta.notebookkotlinfancy.infrastructure.rest.model

import java.time.LocalDateTime
import java.util.*

class ReadNoteDto(
    val id: UUID,
    val creationDate: LocalDateTime,
    val content: String,
)
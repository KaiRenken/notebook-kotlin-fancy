package de.neusta.notebookkotlinfancy.domain

import java.time.LocalDateTime
import java.util.*

class Note(
    val id: UUID = UUID.randomUUID(),
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val content: String
) {
    init {
        require(content.isNotBlank()) { "Content must not be blank" }
    }
}
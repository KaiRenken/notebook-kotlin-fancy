package de.neusta.notebookkotlinfancy.domain

import java.time.LocalDateTime
import java.util.*

class Note private constructor(
    val id: UUID = UUID.randomUUID(),
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val content: String
) {
    init {
        require(content.isNotBlank()) { "Content must not be blank" }
    }

    companion object {
        operator fun invoke(
            content: String
        ) = Note(
            content = content
        )

        operator fun invoke(
            id: UUID,
            creationDate: LocalDateTime,
            content: String,
        ) = Note(
            id = id,
            creationDate = creationDate,
            content = content,
        )
    }
}
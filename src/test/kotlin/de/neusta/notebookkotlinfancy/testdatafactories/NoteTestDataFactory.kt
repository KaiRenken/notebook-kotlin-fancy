package de.neusta.notebookkotlinfancy.testdatafactories

import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.infrastructure.repository.model.NoteEntity
import java.time.LocalDateTime
import java.util.*

fun aTestNote(
    id: UUID = UUID.randomUUID(),
    creationDate: LocalDateTime = LocalDateTime.now(),
    content: String = "test-content",
): Note = Note(
    id = id,
    creationDate = creationDate,
    content = content,
)

fun aTestNoteEntity(
    id: UUID = UUID.randomUUID(),
    creationDate: LocalDateTime = LocalDateTime.now(),
    content: String = "test-content",
): NoteEntity = NoteEntity(
    id = id,
    creationDate = creationDate,
    content = content,
)
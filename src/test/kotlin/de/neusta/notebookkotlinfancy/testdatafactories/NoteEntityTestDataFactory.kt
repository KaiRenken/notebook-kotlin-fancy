package de.neusta.notebookkotlinfancy.testdatafactories

import de.neusta.notebookkotlinfancy.infrastructure.repository.model.NoteEntity
import java.time.LocalDateTime
import java.util.*

class NoteEntityTestDataFactory {

    private var id = UUID.randomUUID()
    private var creationDate = LocalDateTime.now()
    private var content = "test-content"

    companion object {
        fun aTestNoteEntity(): NoteEntityTestDataFactory {
            return NoteEntityTestDataFactory()
        }
    }

    fun withId(id: UUID): NoteEntityTestDataFactory {
        this.id = id
        return this
    }

    fun withCreationDate(creationDate: LocalDateTime): NoteEntityTestDataFactory {
        this.creationDate = creationDate
        return this
    }

    fun withContent(content: String): NoteEntityTestDataFactory {
        this.content = content
        return this
    }

    fun build(): NoteEntity {
        return NoteEntity(id, creationDate, content)
    }
}
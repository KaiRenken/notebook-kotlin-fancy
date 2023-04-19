package de.neusta.notebookkotlinfancy.testdatafactories

import de.neusta.notebookkotlinfancy.domain.Note
import java.time.LocalDateTime
import java.util.*

class NoteTestDataFactory {

    private var id = UUID.randomUUID()
    private var creationDate = LocalDateTime.now()
    private var content = "test-content"

    companion object {
        fun aTestNote(): NoteTestDataFactory {
            return NoteTestDataFactory()
        }
    }

    fun withId(id: UUID): NoteTestDataFactory {
        this.id = id
        return this
    }

    fun withCreationDate(creationDate: LocalDateTime): NoteTestDataFactory {
        this.creationDate = creationDate
        return this
    }

    fun withContent(content: String): NoteTestDataFactory {
        this.content = content
        return this
    }

    fun build(): Note {
        return Note(id, creationDate, content)
    }
}
package de.neusta.notebookkotlinfancy.application

import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import org.springframework.stereotype.Service

@Service
class NoteCreation(private val noteRepository: NoteRepository) {

    fun createNote(content: String): Note =
        content
            .createDomainObject()
            .storeToDatabase()

    private fun String.createDomainObject(): Note = Note(content = this)

    private fun Note.storeToDatabase(): Note = noteRepository.store(this)
}

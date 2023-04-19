package de.neusta.notebookkotlinfancy.application

import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import org.springframework.stereotype.Service

@Service
class NoteCreation(private val noteRepository: NoteRepository) {

    fun createNote(content: String): Note {
        val createdNote = Note(content = content)

        noteRepository.store(createdNote)

        return createdNote
    }
}
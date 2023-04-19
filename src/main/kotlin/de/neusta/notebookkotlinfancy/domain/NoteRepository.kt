package de.neusta.notebookkotlinfancy.domain

import org.springframework.stereotype.Repository

@Repository
interface NoteRepository {

    fun store(note: Note): Note

    fun findAll(): List<Note>
}
package de.neusta.notebookkotlinfancy.infrastructure.rest

import de.neusta.notebookkotlinfancy.application.NoteCreation
import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import de.neusta.notebookkotlinfancy.infrastructure.rest.model.CreateNoteDto
import de.neusta.notebookkotlinfancy.infrastructure.rest.model.ReadNoteDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NoteController(
    private val noteCreation: NoteCreation,
    private val noteRepository: NoteRepository,
) {
    @PostMapping("/api/note")
    fun createNote(@RequestBody createNoteDto: CreateNoteDto): ResponseEntity<ReadNoteDto> {
        val creationResult = noteCreation.createNote(createNoteDto.content)
        return ResponseEntity(mapToDto(creationResult), HttpStatus.CREATED)
    }

    @GetMapping("/api/note")
    fun retrieveAllNotes(): ResponseEntity<List<ReadNoteDto>> {
        val retrievedNotes = noteRepository.findAll()
        return ResponseEntity(mapToDto(retrievedNotes), HttpStatus.OK)
    }

    private fun mapToDto(note: Note): ReadNoteDto {
        return ReadNoteDto(
            note.id,
            note.creationDate,
            note.content,
        )
    }

    private fun mapToDto(notes: List<Note>): List<ReadNoteDto> {
        return notes.map { note -> mapToDto(note) }
    }
}
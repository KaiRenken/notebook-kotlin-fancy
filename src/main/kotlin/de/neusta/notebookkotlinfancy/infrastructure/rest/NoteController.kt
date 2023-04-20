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
    fun createNote(@RequestBody createNoteDto: CreateNoteDto): ResponseEntity<ReadNoteDto> =
        createNoteDto
            .callCreationUseCase()
            .toReadNoteDto()
            .wrapInCreatedResponse()

    @GetMapping("/api/note")
    fun retrieveAllNotes(): ResponseEntity<List<ReadNoteDto>> =
        noteRepository
            .findAll()
            .toReadNoteDto()
            .wrapInOkResponse()

    private fun CreateNoteDto.callCreationUseCase(): Note = noteCreation.createNote(this.content)

    private fun ReadNoteDto.wrapInCreatedResponse(): ResponseEntity<ReadNoteDto> =
        ResponseEntity(this, HttpStatus.CREATED)

    private fun Note.toReadNoteDto(): ReadNoteDto = ReadNoteDto(
        id = this.id,
        creationDate = this.creationDate,
        content = this.content,
    )

    private fun List<Note>.toReadNoteDto(): List<ReadNoteDto> = this.map { it.toReadNoteDto() }

    private fun List<ReadNoteDto>.wrapInOkResponse(): ResponseEntity<List<ReadNoteDto>> =
        ResponseEntity(this, HttpStatus.OK)
}

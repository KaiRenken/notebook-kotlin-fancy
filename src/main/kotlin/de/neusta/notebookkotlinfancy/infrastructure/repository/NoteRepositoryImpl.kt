package de.neusta.notebookkotlinfancy.infrastructure.repository

import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import de.neusta.notebookkotlinfancy.infrastructure.repository.model.NoteEntity
import org.springframework.stereotype.Repository

@Repository
class NoteRepositoryImpl(private val noteJpaRepository: NoteJpaRepository) : NoteRepository {

    override fun store(note: Note): Note =
        note
            .toEntity()
            .saveToDb()
            .toDomain()

    override fun findAll(): List<Note> =
        noteJpaRepository
            .findAll()
            .toDomain()

    private fun Note.toEntity(): NoteEntity = NoteEntity(
        id = this.id,
        creationDate = this.creationDate,
        content = this.content,
    )

    private fun NoteEntity.saveToDb(): NoteEntity = noteJpaRepository.save(this)

    private fun NoteEntity.toDomain(): Note = Note(
        id = this.id,
        creationDate = this.creationDate,
        content = this.content,
    )

    private fun List<NoteEntity>.toDomain(): List<Note> =
        this.map { it.toDomain() }
}

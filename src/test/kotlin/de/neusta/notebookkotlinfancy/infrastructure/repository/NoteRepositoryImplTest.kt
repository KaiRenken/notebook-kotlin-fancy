package de.neusta.notebookkotlinfancy.infrastructure.repository

import de.neusta.notebookkotlinfancy.testcontainers.AbstractDatabaseTest
import de.neusta.notebookkotlinfancy.testdatafactories.NoteEntityTestDataFactory.Companion.aTestNoteEntity
import de.neusta.notebookkotlinfancy.testdatafactories.NoteTestDataFactory.Companion.aTestNote
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.util.*
import java.util.List

@Import(NoteRepositoryImpl::class)
class NoteRepositoryImplTest : AbstractDatabaseTest() {

    @Autowired
    private lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Test
    fun storeNote() {
        val noteToStore = aTestNote().build()

        noteRepositoryImpl.store(noteToStore)

        assertThat(noteJpaRepository.count()).isEqualTo(1)

        val storedNote = noteJpaRepository.findAll()[0]

        assertThat(storedNote.id).isEqualTo(noteToStore.id)
        assertThat(storedNote.creationDate).isEqualTo(noteToStore.creationDate)
        assertThat(storedNote.content).isEqualTo(noteToStore.content)
    }

    @Test
    fun findAllNotes() {
        val note1ToFind = aTestNoteEntity().withContent("test-note-1").build()
        val note2ToFind = aTestNoteEntity().withContent("test-note-2").build()

        noteJpaRepository.saveAll(List.of(note1ToFind, note2ToFind))

        val result = noteRepositoryImpl.findAll()

        assertThat(result).hasSize(2)
        assertThat(result[0].id).isEqualTo(note1ToFind.id)
        assertThat(result[0].creationDate).isEqualTo(note1ToFind.creationDate)
        assertThat(result[0].content).isEqualTo(note1ToFind.content)
        assertThat(result[1].id).isEqualTo(note2ToFind.id)
        assertThat(result[1].creationDate).isEqualTo(note2ToFind.creationDate)
        assertThat(result[1].content).isEqualTo(note2ToFind.content)
    }
}
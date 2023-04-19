package de.neusta.notebookkotlinfancy.infrastructure.repository

import de.neusta.notebookkotlinfancy.matchers.shouldBeEqualTo
import de.neusta.notebookkotlinfancy.testcontainers.AbstractDatabaseTest
import de.neusta.notebookkotlinfancy.testdatafactories.aTestNote
import de.neusta.notebookkotlinfancy.testdatafactories.aTestNoteEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.util.*

@Import(NoteRepositoryImpl::class)
class NoteRepositoryImplTest : AbstractDatabaseTest() {

    @Autowired
    private lateinit var noteRepositoryImpl: NoteRepositoryImpl

    @Test
    fun storeNote() {
        val noteToStore = aTestNote()

        noteRepositoryImpl.store(noteToStore)

        noteJpaRepository.count() shouldBe 1
        noteJpaRepository.findAll()[0] shouldBeEqualTo noteToStore
    }

    @Test
    fun findAllNotes() {
        val note1ToFind = aTestNoteEntity(content = "test-content-1")
        val note2ToFind = aTestNoteEntity(content = "test-content-2")

        noteJpaRepository.saveAll(listOf(note1ToFind, note2ToFind))

        val result = noteRepositoryImpl.findAll()

        result.size shouldBe 2
        result[0] shouldBeEqualTo note1ToFind
        result[1] shouldBeEqualTo note2ToFind
    }
}
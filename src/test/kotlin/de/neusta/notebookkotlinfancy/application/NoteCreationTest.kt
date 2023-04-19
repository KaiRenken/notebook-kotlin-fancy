package de.neusta.notebookkotlinfancy.application

import de.neusta.notebookkotlinfancy.domain.NoteRepository
import de.neusta.notebookkotlinfancy.matchers.shouldBeEqualTo
import de.neusta.notebookkotlinfancy.testdatafactories.aTestNote
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class NoteCreationTest {

    private val noteRepositoryMock: NoteRepository = mockk()

    private val noteCreationToTest = NoteCreation(noteRepositoryMock)

    @Test
    fun createNote() {
        val testNote = aTestNote()
        val result = noteCreationToTest.createNote(testNote.content)

        result shouldBeEqualTo testNote

        verify {
            noteRepositoryMock.store(withArg {
                it shouldBeEqualTo testNote
            })
        }
    }
}
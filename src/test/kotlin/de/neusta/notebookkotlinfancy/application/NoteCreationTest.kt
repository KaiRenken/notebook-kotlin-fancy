package de.neusta.notebookkotlinfancy.application

import de.neusta.notebookkotlinfancy.domain.NoteRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NoteCreationTest {

    private val noteRepositoryMock = mock(NoteRepository::class.java)

    private val noteCreationToTest = NoteCreation(noteRepositoryMock)

    @Test
    fun createNote() {
        val content = "test-content"
        val result = noteCreationToTest.createNote(content)
        assertThat(result.id).isNotNull
        assertThat(result.creationDate)
            .isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS))
        assertThat(result.content).isEqualTo("test-content")

        verify(noteRepositoryMock).store(result)
    }
}
package de.neusta.notebookkotlinfancy.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@DisplayName("Create Note")
class NoteTest {

    @Nested
    @DisplayName("successfully")
    inner class CreateNoteSuccessfullyTest {

        @Test
        fun withMinimalParameters() {
            val result = Note(content = "test-content")

            assertThat(result.id).isNotNull
            assertThat(result.creationDate)
                .isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS))
            assertThat(result.content).isEqualTo("test-content")
        }

        @Test
        fun withMaximalParameters() {
            val id = UUID.randomUUID()
            val creationDate = LocalDateTime.now()
            val content = "test-content"

            val result = Note(id, creationDate, content)

            assertThat(result.id).isEqualTo(id)
            assertThat(result.creationDate).isEqualTo(creationDate)
            assertThat(result.content).isEqualTo(content)
        }
    }

    @Test
    fun `with invalid content`() {
        assertThatThrownBy { Note(content = " ") }.isInstanceOf(
            IllegalArgumentException::class.java
        )
            .hasMessage("Content must not be blank")
    }
}
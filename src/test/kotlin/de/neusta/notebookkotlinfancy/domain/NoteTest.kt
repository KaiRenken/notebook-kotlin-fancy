package de.neusta.notebookkotlinfancy.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.date.shouldBeWithin
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@DisplayName("Create Note")
class NoteTest {

    @Nested
    @DisplayName("successfully")
    inner class CreateNoteSuccessfullyTest {

        @Test
        fun withMinimalParameters() {
            val result = Note(content = "test-content")

            result.creationDate.shouldBeWithin(Duration.ofSeconds(1L), LocalDateTime.now())
            result.content shouldBe "test-content"
        }

        @Test
        fun withMaximalParameters() {
            val id = UUID.randomUUID()
            val creationDate = LocalDateTime.now()
            val content = "test-content"

            val result = Note(id, creationDate, content)

            result.id shouldBe id
            result.creationDate shouldBe creationDate
            result.content shouldBe content
        }
    }

    @Test
    fun `with invalid content`() {
        shouldThrow<IllegalArgumentException> {
            Note(content = " ")
        }
            .message shouldBe "Content must not be blank"
    }
}
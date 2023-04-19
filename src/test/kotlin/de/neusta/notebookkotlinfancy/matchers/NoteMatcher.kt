package de.neusta.notebookkotlinfancy.matchers

import de.neusta.notebookkotlinfancy.domain.Note
import de.neusta.notebookkotlinfancy.infrastructure.repository.model.NoteEntity
import io.kotest.matchers.date.shouldBeWithin
import io.kotest.matchers.shouldBe
import java.time.Duration

infix fun Note.shouldBeEqualTo(other: Note) {
    this.creationDate.shouldBeWithin(Duration.ofSeconds(10L), other.creationDate)
    this.content shouldBe other.content
}

infix fun NoteEntity.shouldBeEqualTo(other: Note) {
    this.id shouldBe other.id
    this.creationDate shouldBe other.creationDate
    this.content shouldBe other.content
}

infix fun Note.shouldBeEqualTo(other: NoteEntity) {
    this.id shouldBe other.id
    this.creationDate shouldBe other.creationDate
    this.content shouldBe other.content
}
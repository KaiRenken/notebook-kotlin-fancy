package de.neusta.notebookkotlinfancy.matchers

import de.neusta.notebookkotlinfancy.domain.Note
import io.kotest.matchers.shouldBe

infix fun Note.shouldBeEqualTo(other: Note) {
    this.creationDate shouldBe other.creationDate
    this.content shouldBe other.content
}
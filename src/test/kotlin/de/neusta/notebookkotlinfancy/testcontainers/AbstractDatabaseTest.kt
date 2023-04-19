package de.neusta.notebookkotlinfancy.testcontainers

import de.neusta.notebookkotlinfancy.infrastructure.repository.NoteJpaRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ContextConfiguration(initializers = [PostgresContextInitializer::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class AbstractDatabaseTest {

    @Autowired
    protected lateinit var noteJpaRepository: NoteJpaRepository

    @BeforeEach
    fun clearDb() {
        noteJpaRepository.deleteAll()
    }
}
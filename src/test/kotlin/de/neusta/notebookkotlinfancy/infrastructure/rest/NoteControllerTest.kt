package de.neusta.notebookkotlinfancy.infrastructure.rest

import de.neusta.notebookkotlinfancy.application.NoteCreation
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import de.neusta.notebookkotlinfancy.testdatafactories.NoteTestDataFactory.Companion.aTestNote
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.lang.String
import java.time.format.DateTimeFormatter
import java.util.List

@WebMvcTest(NoteController::class)
class NoteControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var noteCreationMock: NoteCreation

    @MockBean
    private lateinit var noteRepositoryMock: NoteRepository

    @Test
    fun createNote() {
        val createdNote = aTestNote().build()
        val requestBody = """
                {
                    "content": "test-content"
                }
                """
        val expectedResponse = String.format(
            """
                {
                    "id": "%s",
                    "creationDate": "%s",
                    "content": "%s"
                }
                """,
            createdNote.id,
            createdNote.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")),
            createdNote.content
        )

        `when`(noteCreationMock.createNote("test-content")).thenReturn(createdNote)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
            .andExpect(status().isCreated)
            .andExpect(content().json(expectedResponse, true))
    }

    @Test
    fun retrieveAllNotes() {
        val note1 = aTestNote().withContent("test-content-1").build()
        val note2 = aTestNote().withContent("test-content-2").build()
        val expectedResponse = String.format(
            """
                [
                    {
                        "id": "%s",
                        "creationDate": "%s",
                        "content": "%s"
                    },
                    {
                        "id": "%s",
                        "creationDate": "%s",
                        "content": "%s"
                    }
                ]
                """,
            note1.id,
            note1.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")),
            note1.content,
            note2.id,
            note2.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")),
            note2.content
        )

        `when`(noteRepositoryMock.findAll()).thenReturn(List.of(note1, note2))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/note"))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedResponse, true))
    }
}
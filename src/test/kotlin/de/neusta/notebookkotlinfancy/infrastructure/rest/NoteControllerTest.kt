package de.neusta.notebookkotlinfancy.infrastructure.rest

import com.ninjasquad.springmockk.MockkBean
import de.neusta.notebookkotlinfancy.application.NoteCreation
import de.neusta.notebookkotlinfancy.domain.NoteRepository
import de.neusta.notebookkotlinfancy.testdatafactories.aTestNote
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.format.DateTimeFormatter

@WebMvcTest(NoteController::class)
class NoteControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var noteCreationMock: NoteCreation

    @MockkBean
    private lateinit var noteRepositoryMock: NoteRepository

    @Test
    fun createNote() {
        val createdNote = aTestNote()
        val requestBody = """
                {
                    "content": "${createdNote.content}"
                }
                """
        val expectedResponse = """
                {
                    "id": "${createdNote.id}",
                    "creationDate": "${createdNote.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"))}",
                    "content": "${createdNote.content}"
                }
                """

        every { noteCreationMock.createNote(createdNote.content) }
            .returns(createdNote)

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
        val note1 = aTestNote(content = "test-content-1")
        val note2 = aTestNote(content = "test-content-2")
        val expectedResponse = """
                [
                    {
                        "id": "${note1.id}",
                        "creationDate": "${note1.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"))}",
                        "content": "${note1.content}"
                    },
                    {
                        "id": "${note2.id}",
                        "creationDate": "${note2.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"))}",
                        "content": "${note2.content}"
                    }
                ]
                """

        every { noteRepositoryMock.findAll() }.returns(listOf(note1, note2))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/note"))
            .andExpect(status().isOk)
            .andExpect(content().json(expectedResponse, true))
    }
}
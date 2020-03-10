package com.thescore.nbateamviewer.domain

import com.thescore.nbateamviewer.api.dto.PlayerDto
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Kanghee Lee
 */
class PlayerDtoMapperTest {
    private val mapper = PlayerDtoMapper()

    @Test
    fun validateDtoMapping() {
        val dto = PlayerDto(
            id = 123,
            firstName = "first name",
            lastName = "last name",
            number = 5,
            position = "PG"
        )

        val model = mapper.convertDto(dto)
        assertEquals(dto.id, model.id)
        assertEquals(dto.firstName, model.firstName)
        assertEquals(dto.lastName, model.lastName)
        assertEquals(dto.number, model.number)
        assertEquals(dto.position, model.position)
    }
}
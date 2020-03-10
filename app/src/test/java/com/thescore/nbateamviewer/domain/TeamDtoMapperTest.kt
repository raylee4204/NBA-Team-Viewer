package com.thescore.nbateamviewer.domain

import com.thescore.nbateamviewer.api.dto.PlayerDto
import com.thescore.nbateamviewer.api.dto.TeamDto
import com.thescore.nbateamviewer.domain.model.Player
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as _when

/**
 * Created by Kanghee Lee
 */
@RunWith(MockitoJUnitRunner::class)
class TeamDtoMapperTest {

    @Mock
    private lateinit var playerDtoMapper: PlayerDtoMapper

    @Mock
    private lateinit var playerDtos: List<PlayerDto>

    @Mock
    private lateinit var players: List<Player>

    private lateinit var mapper: TeamDtoMapper

    @Before
    fun setup() {
        mapper = TeamDtoMapper(playerDtoMapper)
        _when(playerDtoMapper.convertDtos(ArgumentMatchers.anyList())).thenReturn(players)
    }

    @Test
    fun validateDtoMapping() {
        val dto = TeamDto(
            id = 123,
            fullName = "team name",
            wins = 1,
            losses = 0,
            players = playerDtos
        )

        val model = mapper.convertDto(dto)
        assertEquals(dto.id, model.id)
        assertEquals(dto.fullName, model.fullName)
        assertEquals(dto.wins, model.wins)
        assertEquals(dto.losses, model.losses)
        assertEquals(dto.players.size, model.players.size)
    }
}
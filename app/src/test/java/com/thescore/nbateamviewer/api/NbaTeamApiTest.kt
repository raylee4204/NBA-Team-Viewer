package com.thescore.nbateamviewer.api

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.assignment.vo.Status.ERROR
import com.squareup.assignment.vo.Status.SUCCESS
import com.thescore.nbateamviewer.api.dto.TeamDto
import com.thescore.nbateamviewer.domain.TeamDtoMapper
import com.thescore.nbateamviewer.domain.model.Team
import com.thescore.nbateamviewer.vo.TeamSort.ALPHABETIC
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

/**
 * Created by Kanghee Lee
 */
@RunWith(MockitoJUnitRunner::class)
class NbaTeamApiTest {

    private val teamDtoMapper: TeamDtoMapper = mock()
    private val teamDto =
        TeamDto(id = 1, fullName = "team name", wins = 1, losses = 0, players = emptyList())
    private val team =
        Team(id = 1, fullName = "team name", wins = 1, losses = 0, players = emptyList())

    @Before
    fun setup() {
        whenever(teamDtoMapper.convertDtos(anyList())).thenReturn(listOf(team))
    }

    @Test
    fun testListTeams() = runBlocking {
        val service = mock<NbaTeamService> {
            onBlocking { listTeams() } doReturn (Response.success(listOf(teamDto)))
        }
        val api = NbaTeamApi(service, teamDtoMapper)
        val response = api.listTeams(sort = ALPHABETIC)
        assertEquals(SUCCESS, response.status)
        assertNotNull(response.data)
        assertEquals(listOf(team), response.data)
    }

    @Test
    fun testListTeamsError() = runBlocking {
        val service = mock<NbaTeamService> {
            onBlocking { listTeams() } doReturn (Response.error(
                500, "error".toResponseBody()
            ))
        }
        val api = NbaTeamApi(service, teamDtoMapper)
        val response = api.listTeams(sort = ALPHABETIC)
        assertEquals(ERROR, response.status)
        assertNotNull(response.data)
        assertTrue(response.data!!.isEmpty())
    }
}
package com.thescore.nbateamviewer.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.thescore.nbateamviewer.api.dto.TeamDto

/**
 * Created by Kanghee Lee
 */
class TeamsJsonAdapter {

    @FromJson
    fun fromJson(reader: JsonReader, jsonAdapter: JsonAdapter<TeamDto>): List<TeamDto>? {
        val list = ArrayList<TeamDto>()
        if (reader.hasNext()) {
            val token = reader.peek()
            if (token == JsonReader.Token.BEGIN_ARRAY) {
                reader.beginArray()
                while (reader.hasNext()) {
                    val yourResponse = jsonAdapter.fromJsonValue(reader.readJsonValue())
                    yourResponse?.let {
                        list.add(yourResponse)
                    }
                }
                reader.endArray()
            }
        }
        return list.toList()
    }
}
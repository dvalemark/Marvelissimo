package com.projects.disav.marvelissimo.network.api

import com.projects.disav.marvelissimo.network.api.dto.characters.CharacterDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters(): Single<CharacterDataWrapper>

    @GET("characters")
    fun getCharactersByNameContaining(@Query("nameStartsWith") nameStart: String): Single<CharacterDataWrapper>
}
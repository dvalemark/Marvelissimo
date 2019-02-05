package com.projects.disav.marvelissimo.network.api

import com.projects.disav.marvelissimo.network.api.dto.characters.CharacterDataWrapper
import com.projects.disav.marvelissimo.network.api.dto.comics.ComicsDataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters(): Single<CharacterDataWrapper>

    @GET("characters")
    fun getCharactersByNameStartingWith(@Query("nameStartsWith") nameStart: String): Single<CharacterDataWrapper>

    @GET("characters/{id}")
    fun getOneCharacter(@Path("id") searchVal :Int): Single<CharacterDataWrapper>

    @GET("comics")
    fun getAllComic(): Single<ComicsDataWrapper>

    @GET("comics")
    fun getComicsByTitleStartingWith(@Query("titleStartsWith")nameStart: String): Single<ComicsDataWrapper>

    @GET("comics/{id}")
    fun getOneComic(@Path("id")searchVal: Int):Single<ComicsDataWrapper>
}
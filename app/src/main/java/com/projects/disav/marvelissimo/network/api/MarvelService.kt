package com.projects.disav.marvelissimo.network.api

import com.projects.disav.marvelissimo.network.api.dto.characters.Character
import com.projects.disav.marvelissimo.network.api.dto.characters.CharacterDataContainer
import com.projects.disav.marvelissimo.network.api.dto.characters.CharacterDataWrapper
import com.projects.disav.marvelissimo.network.api.dto.comics.ComicsDataWrapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters(@Query("offset") offset : Int): Single<CharacterDataWrapper>

    @GET("characters")
    fun getCharactersByNameStartingWith(@Query("nameStartsWith") nameStart: String, @Query("offset") offset : Int): Single<CharacterDataWrapper>

    @GET("characters/{id}")
    fun getOneCharacter(@Path("id") searchVal :Int): Single<CharacterDataWrapper>

    @GET("comics")
    fun getAllComic(@Query("offset") offset : Int): Single<ComicsDataWrapper>

    @GET("comics")
    fun getComicsByTitleStartingWith(@Query("titleStartsWith")nameStart: String, @Query("offset") offset : Int): Single<ComicsDataWrapper>

    @GET("comics/{id}")
    fun getOneComic(@Path("id")searchVal: Int):Single<ComicsDataWrapper>
}
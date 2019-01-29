package com.projects.disav.marvelissimo.api

import retrofit2.http.GET

interface MarvelService {
    @GET("Characters")
    fun getAllCharacters(): Single<CharacterDataWrapper>

}
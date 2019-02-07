package com.projects.disav.marvelissimo.network.api.dto.characters

data class Character (val id: Int, val name: String, val description: String , val thumbnail: Thumbnail, val urls: List<Url>, val comics: ComicsList, val series: SeriesList)

data class ComicsList(val items: Array<ComicSummary>)

data class ComicSummary(val name: String)

data class SeriesList(val items: Array<SeriesSummary>)

data class SeriesSummary(val name: String)
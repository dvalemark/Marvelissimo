package com.projects.disav.marvelissimo.network.api.dto.characters

data class Character (val id: Int, val name: String, val description: String , val thumbnail: Thumbnail, val urls: List<Url>)
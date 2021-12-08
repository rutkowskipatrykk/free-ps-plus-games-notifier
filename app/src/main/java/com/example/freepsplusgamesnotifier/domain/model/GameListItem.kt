package com.example.freepsplusgamesnotifier.domain.model

data class GameListItem (
    val id: Int,
    val name: String,
    val rating: Double,
    val cover: String?,
    val platforms: String
) {

    companion object {
        fun getMockItem() = GameListItem(0, "Testtest", 99.0, "", "")
    }

}
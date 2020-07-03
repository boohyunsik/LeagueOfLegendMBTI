package model

data class MatchList(
    val startIndex: Int,
    val totalGames: Int,
    val endIndex: Int,
    val matches: List<MatchReference>
)
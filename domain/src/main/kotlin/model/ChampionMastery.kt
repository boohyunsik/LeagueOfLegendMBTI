package model

data class ChampionMastery(
    val championPointsUntilNextLevel: Long,
    val chestGranted: Boolean,
    val championId: Long,
    val lastPlayTime: Long,
    val championLevel: Int,
    val summonerId: String,
    val championPoints: Int,
    val championPointsSinceLastLevel: Long,
    val tokensEarned: Int
)
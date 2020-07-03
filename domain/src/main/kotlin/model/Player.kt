package model

data class Player(
    val profileIcon: Int,
    val accountId: String,
    val matchHistoryUri: String,
    val currentAccountId: String,
    val currentPlatformId: String,
    val summonerName: String,
    val summonerId: String,
    val platformId: String)
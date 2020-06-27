package api

import model.Summoner

class RiotService(private val riotApi: RiotApi) {
    suspend fun getSummonerInfo(): Summoner = riotApi.getSummonerInfo()
}
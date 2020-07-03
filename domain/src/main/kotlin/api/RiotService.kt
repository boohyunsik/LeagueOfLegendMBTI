package api

import model.Match
import model.MatchList
import model.Summoner

class RiotService(private val riotApi: RiotApi) {
    suspend fun getSummonerInfo(summonerName: String): Summoner = riotApi.getSummonerInfo(summonerName)
    suspend fun getMatchListByEncryptedId(encryptionId: String): MatchList = riotApi.getMatchInfoByEncryptedAccountId(encryptionId)
    suspend fun getMatch(matchId: String): Match = riotApi.getMatch(matchId)
}
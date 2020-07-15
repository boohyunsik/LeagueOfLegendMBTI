package api

import exception.MatchNotFoundException
import exception.SummonerNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.ChampionMastery
import model.Match
import model.MatchList
import model.Summoner

class RiotService(private val riotApi: RiotApi) {
    suspend fun getSummonerInfo(summonerName: String): Summoner {
        try {
            return riotApi.getSummonerInfo(summonerName)
        } catch (exception: Exception) {
            // TODO : Handling exception more briefly!
            throw SummonerNotFoundException(summonerName)
        }
    }
    suspend fun getMatchListByEncryptedId(encryptionId: String): MatchList {
        try {
            return riotApi.getMatchInfoByEncryptedAccountId(encryptionId)
        } catch (exception: Exception) {
            // TODO : Handling exception more briefly!
            throw SummonerNotFoundException("$encryptionId(encrypted Id)")
        }
    }

    suspend fun getMatch(matchId: String): Match {
        try {
            return riotApi.getMatch(matchId)
        } catch (exception: Exception) {
            // TODO : Handling exception more briefly!
            throw MatchNotFoundException(matchId)
        }
    }

    suspend fun getChampionMasteryListByEncryptedAccountId(encryptionId: String): List<ChampionMastery> {
        return riotApi.getChampionMasteryListByEncryptedAccountId(encryptionId)
    }
}
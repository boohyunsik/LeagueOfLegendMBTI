package api

import model.ChampionMastery
import model.Match
import model.MatchList
import model.Summoner
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerInfo(@Path("summonerName") summonerName: String): Summoner

    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}")
    suspend fun getMatchInfoByEncryptedAccountId(@Path("encryptedAccountId") encryptedAccountId: String): MatchList

    @GET("/lol/match/v4/matches/{matchId}")
    suspend fun getMatch(@Path("matchId") matchId: String): Match

    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedAccountId}")
    suspend fun getChampionMasteryListByEncryptedAccountId(@Path("encryptedAccountId") encryptedAccountId: String): List<ChampionMastery>
}
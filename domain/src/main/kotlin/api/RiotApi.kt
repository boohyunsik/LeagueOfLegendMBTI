package api

import model.Summoner
import retrofit2.http.GET

interface RiotApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerInfo(): Summoner
}
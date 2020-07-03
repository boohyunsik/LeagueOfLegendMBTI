interface DomainService {
    suspend fun getRecentMatchBySummonerName(summonerName: String)

    suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int)

}
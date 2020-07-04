import model.Match
import model.MatchReference
import model.Summoner

interface DomainService {
    suspend fun getRecentMatchBySummonerName(summonerName: String): MatchReference

    suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int): List<MatchReference>

    suspend fun getMatchDetailByGameId(gameId: String): Match
}
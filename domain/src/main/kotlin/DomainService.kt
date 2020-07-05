import model.Match
import model.MatchReference

interface DomainService {
    suspend fun getRecentMatchBySummonerName(summonerName: String): MatchReference

    suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int): List<MatchReference>

    suspend fun getRecentMatchesDetailBySummonerName(summonerName: String, numberOfMatch: Int): List<Match>

    suspend fun getMatchDetailByGameId(gameId: String): Match
}
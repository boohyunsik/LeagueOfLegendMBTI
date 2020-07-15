import model.*

interface DomainService {
    suspend fun getCurrentPatchVersion(): String

    suspend fun getChampionKoreanInfo(version: String, championName: String): DDragonFormat

    suspend fun getRecentMatchBySummonerName(summonerName: String): MatchReference

    suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int): List<MatchReference>

    suspend fun getRecentMatchesDetailBySummonerName(summonerName: String, numberOfMatch: Int): List<Match>

    suspend fun getMatchDetailByGameId(gameId: String): Match

    suspend fun getSummonerInfoByName(summonerName: String): Summoner

    suspend fun getChampionMasteryListByEncryptedId(encryptedId: String): List<ChampionMastery>
}
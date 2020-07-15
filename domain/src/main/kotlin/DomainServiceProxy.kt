import api.DataDragonService
import api.RiotService
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import model.ChampionMastery
import model.Match
import model.MatchReference
import model.Summoner
import retrofit2.Response

class DomainServiceProxy(private val riotService: RiotService,
                         private val dataDragonService: DataDragonService): DomainService {
    override suspend fun getCurrentPatchVersion() = dataDragonService.getVersion()[0]

    override suspend fun getChampionKoreanInfo(version: String, championName: String)
            = dataDragonService.getChampionKoreanInfo(version, championName)

    override suspend fun getRecentMatchBySummonerName(summonerName: String): MatchReference {
        val summoner =  riotService.getSummonerInfo(summonerName)
        return riotService.getMatchListByEncryptedId(summoner.accountId).matches[0]
    }

    override suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int): List<MatchReference> {
        val summoner = riotService.getSummonerInfo(summonerName)
        val matches = riotService.getMatchListByEncryptedId(summoner.accountId)
        return matches.matches.subList(0, numberOfMatch)
    }

    override suspend fun getRecentMatchesDetailBySummonerName(summonerName: String, numberOfMatch: Int): List<Match> {
        val summoner = riotService.getSummonerInfo(summonerName)
        val matchReference = riotService.getMatchListByEncryptedId(summoner.accountId)
        return matchReference.matches.subList(0, numberOfMatch)
            .asFlow()
            .map { riotService.getMatch("${it.gameId}") }
            .toList()
    }

    override suspend fun getMatchDetailByGameId(gameId: String): Match {
        return riotService.getMatch(gameId)
    }

    override suspend fun getSummonerInfoByName(summonerName: String): Summoner {
        return riotService.getSummonerInfo(summonerName)
    }

    override suspend fun getChampionMasteryListByEncryptedId(encryptedId: String): List<ChampionMastery> {
        return riotService.getChampionMasteryListByEncryptedAccountId(encryptedId)
    }

    companion object {
        const val TAG = "DomainServiceProxy"
    }
}
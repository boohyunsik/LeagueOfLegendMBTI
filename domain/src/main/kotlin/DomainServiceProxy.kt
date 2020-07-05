import api.RiotService
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import model.Match
import model.MatchReference

class DomainServiceProxy(private val riotService: RiotService): DomainService {
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

    companion object {
        const val TAG = "DomainServiceProxy"
    }
}
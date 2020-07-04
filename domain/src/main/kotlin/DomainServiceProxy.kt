import api.RiotService
import model.Match
import model.MatchReference
import model.Summoner

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

    override suspend fun getMatchDetailByGameId(gameId: String): Match {
        return riotService.getMatch(gameId)
    }

    companion object {
        const val TAG = "DomainServiceProxy"
    }
}
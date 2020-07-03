import api.RiotApi

class DomainServiceProxy: DomainService {

    private lateinit var riotApi: RiotApi

    override suspend fun getRecentMatchBySummonerName(summonerName: String) {
        val summonerInfo = riotApi.getSummonerInfo(summonerName)
        
    }

    override suspend fun getRecentMatchesBySummonerName(summonerName: String, numberOfMatch: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
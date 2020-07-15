package champion

import DomainService

class ChampionAnalyser(private val domainService: DomainService) {
    suspend fun getMostChampion(summonerName: String) {
        val summonerInfo = domainService.getSummonerInfoByName(summonerName)
        val championMasteryList = domainService.getChampionMasteryListByEncryptedId(summonerInfo.accountId)
    }
}
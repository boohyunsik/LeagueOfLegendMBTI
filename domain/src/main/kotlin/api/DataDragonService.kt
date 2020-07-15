package api

import model.DDragonFormat

class DataDragonService(private val dataDragonApi: DataDragonApi) {
    suspend fun getVersion() = dataDragonApi.getVersion()

    suspend fun getChampionKoreanInfo(version: String, championName: String): DDragonFormat =
        dataDragonApi.getChampionInfo(version, "ko_KR", "${championName}.json")
}
package api

import model.DDragonFormat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataDragonApi {
    @GET("/api/versions.json")
    suspend fun getVersion(): List<String>

    @GET("/cdn/{patchVersion}/data/{language}/champion/{championJsonName}")
    suspend fun getChampionInfo(
        @Path("patchVersion") patchVersion: String,
        @Path("language") language: String,
        @Path("championJsonName") championJsonName: String): DDragonFormat
}
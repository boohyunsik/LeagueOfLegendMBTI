package api

import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RiotServiceTest {
    var riotService: RiotService? = null

    @Before fun setup() {

        val riotApi = Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RiotApi::class.java)
        riotService = RiotService(riotApi)
    }

    @Test fun `getSummonerInfo should return `() {
        runBlockingTest {
            val summoner = riotService?.getSummonerInfo()
            print(summoner)
        }
    }
}
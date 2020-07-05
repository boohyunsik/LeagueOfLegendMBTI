package api

import exception.SummonerNotFoundException
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RiotServiceTest {
    private var riotService: RiotService? = null

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before fun setup() {
        Dispatchers.setMain(testDispatcher)
        val client = OkHttpClient.Builder().addNetworkInterceptor {
            val request = it.request().newBuilder()
                .addHeader("X-Riot-Token", "RGAPI-bee17f34-008d-4fd5-afe1-e7b6ae0c093f")
                .build()
            it.proceed(request)
        }.build()

        val riotApi = Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RiotApi::class.java)

        riotService = RiotService(riotApi)
    }

    @After fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test fun `getSummonerInfo should return valid summoner info`() = runBlocking {
        val summoner = riotService?.getSummonerInfo("hide on bush")
        assertEquals("Hide on bush", summoner?.name)
        print(summoner)
    }

    @Test(expected = SummonerNotFoundException::class)
    fun `if summoner is not exist, getSummonerInfo should throw "NoSummonerException"`() = runBlocking {
        val summoner = riotService?.getSummonerInfo("!@@!@#!@$!@#!@!")
    }

    @Test fun `getMatchList should return valid match list`() = runBlocking {
        val matchList = riotService?.getMatchListByEncryptedId("nRoJslIjLFHySHhIIAJ_RPVlBpdTL_p-T8I-i8qXvgWy")
        matchList?.matches?.forEach {
            println(it)
        }
        println("end..")
    }

    @Test fun `getMatch should return valid match info`() = runBlocking {
        val match = riotService?.getMatch("4481919316")
        println("participantIdentities : ${match?.participantIdentities}")
        println("teams : ${match?.teams}")
        println("participants : ${match?.participants}")
    }

}
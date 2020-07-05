import api.RiotApi
import api.RiotService
import exception.MatchNotFoundException
import exception.SummonerNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DomainServiceProxyTest {
    private lateinit var domainService: DomainServiceProxy

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

        val riotService = RiotService(riotApi)
        domainService = DomainServiceProxy(riotService)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test fun `getRecentMatchBySummonerName should return valid match info`() = runBlocking {
        val match = domainService.getRecentMatchBySummonerName("하위빅스비")
        print(match)
    }

    @Test fun `getMatchDetailByGameId should return valid match info`() = runBlocking {
        val match = domainService.getMatchDetailByGameId("4488936332")
        print(match)
    }

    // Exception tests
    @Test(expected = SummonerNotFoundException::class)
    fun `getRecentMatchBySummonerName with not existed summoner should throw SummonerNotFoundException`() = runBlocking {
        val match = domainService.getRecentMatchBySummonerName("!@#!@#@!#!@#!@")
    }

    @Test(expected = MatchNotFoundException::class)
    fun `getMatchDetailByGameId with not existed game id should throw MatchNotFoundException`() = runBlocking {
        val matchDetail = domainService.getMatchDetailByGameId("aaaaa")
    }
}
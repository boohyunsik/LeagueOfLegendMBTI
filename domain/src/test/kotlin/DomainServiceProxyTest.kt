import api.DataDragonApi
import api.DataDragonService
import api.RiotApi
import api.RiotService
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

    @Before
    fun setup() {
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

        val dataDragonApi = Retrofit.Builder()
            .baseUrl("https://ddragon.leagueoflegends.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataDragonApi::class.java)

        val riotService = RiotService(riotApi)
        val dataDragonService = DataDragonService(dataDragonApi)
        domainService = DomainServiceProxy(riotService, dataDragonService)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getChampionMasteryListByEncryptedId should return valid info`() = runBlocking {
        val summonerInfo = domainService.getSummonerInfoByName("하위빅스비")
        val masteryList = domainService.getChampionMasteryListByEncryptedId(summonerInfo.id)
        print(masteryList)
    }

    @Test
    fun `what getVersion return`() = runBlocking {
        val version = domainService.getCurrentPatchVersion()
        print(version)
    }

    @Test
    fun `what getChampionInfo return`() = runBlocking {
        val version = domainService.getCurrentPatchVersion()
        val championInfo = domainService.getChampionKoreanInfo(version, "Aatrox")
        print(championInfo)
    }
}
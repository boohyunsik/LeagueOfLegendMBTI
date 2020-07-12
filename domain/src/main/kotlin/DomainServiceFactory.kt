import api.RiotApi
import api.RiotService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DomainServiceFactory {

    fun createService(): DomainService {
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

        return DomainServiceProxy(RiotService(riotApi))
    }
}
package exception

class SummonerNotFoundException(val summonerName: String): Exception() {
    override val message: String?
        get() = "Summoner named [$summonerName] is not exist."
}
package exception

class MatchNotFoundException(private val matchId: String): Exception() {
    override val message: String?
        get() = "Match [$matchId] is not exist."
}
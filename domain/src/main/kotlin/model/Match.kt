package model

data class Match(
    val gameId: Long,
    val participantIdentities: List<ParticipantIdentity>,
    val queueId: Int,
    val gameType: String,
    val gameDuration: Long,
    val teams: List<TeamStats>,
    val platformId: String,
    val gameCreation: Long,
    val seasonId: Int,
    val gameVersion: String,
    val mapId: Int,
    val gameMode: String,
    val participants: List<Participant>
)
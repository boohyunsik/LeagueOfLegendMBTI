package model

data class Participant(
    val participantId: Int,
    val championId: Int,
    val runes: List<Rune>,
    val stats: ParticipantStats,
    val teamId: Int,
    val timeline: ParticipantTimeline,
    val spell1ld: Int,
    val spell2ld: Int,
    val highestAchievedSeasonTier: String,
    val masteries: List<Mastery>)
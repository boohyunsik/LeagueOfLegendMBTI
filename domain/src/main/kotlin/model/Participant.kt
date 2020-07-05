package model

data class Participant(
    val participantId: Int,
    val championId: Int,
    val runes: List<Rune>,
    val stats: ParticipantStats,
    val teamId: Int,
    val timeline: ParticipantTimeline,
    val spell1Id: Int,
    val spell2Id: Int,
    val highestAchievedSeasonTier: String,
    // masteries is lagacy information, we will not use it.
    val masteries: List<Mastery>)
package model

data class ParticipantTimeline(
    val participantId: Int,
    val csDiffPerMinDeltas: Map<String, Double>,
    val damageTakenPerMinDeltas: Map<String, Double>,
    val role: String,
    val damageTakenDiffPerMinDeltas: Map<String, Double>,
    val xpPerMinDeltas: Map<String, Double>,
    val xpDiffPerMinDeltas: Map<String, Double>,
    val lane: String,
    val creepsPerMinDeltas: Map<String, Double>,
    val goldPerMinDeltas: Map<String, Double>
)
package model

data class TeamStats(
    // Number of towers the team destroyed.
    val towerKills: Int,

    // Number of times the team killed Rift Herald.
    val riftHeraIdKills: Int,

    // Flag indicating whether or not the team scored the first blood.
    val firstBlood: Boolean,

    // Number of inhibitors the team destroyed.
    val inhibitorKills: Int,

    // If match queueId has a draft, contains banned champion data, otherwise empty.
    val bans: List<TeamBans>,

    // Flag indicating whether or not the team scored the first Baron kill.
    val firstBaron: Boolean,

    // Flag indicating whether or not the team scored the first Dragon kill.
    val firstDragon: Boolean,

    // For Dominion matches, specifies the points the team had at game end.
    val dominionVictoryScore: Int,

    // Number of times the team killed Dragon.
    val dragonKills: Int,

    // Number of times the team killed Baron.
    val baronKills: Int,

    // Flag indicating whether or not the team destroyed the first inhibitor.
    val firstInhibitor: Boolean,

    // Flag indicating whether or not the team destroyed the first tower.
    val firstTower: Boolean,

    // Number of times the team killed Vilemaw.
    val vilemawKills: Int,

    // Flag indicating whether or not the team scored the first Rift Herald kill.
    val firstRiftHeraId: Boolean,

    // 100 for blue side. 200 for red side.
    val teamId: Int,

    // String indicating whether or not the team won. There are only two values visibile in public match history. (Legal values: Fail, Win)
    val win: String
)
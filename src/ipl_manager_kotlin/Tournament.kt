package ipl_manager_kotlin

import java.util.*
import java.util.stream.Collectors

/**
 * Tournament class representing the entire cricket tournament.
 */
class Tournament(private val matchFormat: MatchFormat) {
    val teams = mutableListOf<Team>()
    private val matches: MutableList<Match> = ArrayList()
    private val playerStats: MutableMap<String, Player> = HashMap()
    private val teamScores: MutableMap<String, TeamScore> = HashMap()

    fun addTeam(name: String, homeGround: String, players: List<Player>) {
        val maxPlayersPerTeam = if (matchFormat == MatchFormat.IPL) 12 else 11
        if (players.size <= maxPlayersPerTeam) {
            val team = Team(name, homeGround, players)
            teams.add(team)
            teamScores[name] = TeamScore(name)
            players.forEach { player -> playerStats[player.getName()] = player }
        } else {
            println("Error: Team $name has more than $maxPlayersPerTeam players.")
        }
    }

    fun addMatch(team1: Team, team2: Team, winner: Team?) {
        val match = Match(team1, team2)
        matches.add(match)
        if (winner != null) {
            val winnerScore = teamScores[winner.getNames()]
            winnerScore?.incrementWins()
            winnerScore?.addPoints(2)
        } else {
            val team1Score = teamScores[team1.getNames()]
            team1Score?.addPoints(1)
            val team2Score = teamScores[team2.getNames()]
            team2Score?.addPoints(1)
        }
    }

    fun updatePlayerStats(playerName: String, runsScored: Int, wicketsTaken: Int) {
        val player = playerStats[playerName]
        player?.apply {
            addRunsScored(runsScored)
            addWicketsTaken(wicketsTaken)
            if (runsScored >= 50) {
                incrementFiftiesCount()
            }
        }
    }

    fun getHighestWicketTaker(): Player? {
        var highestWicketTaker: Player? = null
        var maxWickets = 0
        playerStats.values.forEach { player ->
            if (player.getWicketsTaken() > maxWickets) {
                maxWickets = player.getWicketsTaken()
                highestWicketTaker = player
            }
        }
        return highestWicketTaker
    }

    fun getMaxFiftiesScorer(): Player? {
        var maxFiftiesScorer: Player? = null
        var maxFifties = 0
        playerStats.values.forEach { player ->
            if (player.getFiftiesCount() > maxFifties) {
                maxFifties = player.getFiftiesCount()
                maxFiftiesScorer = player
            }
        }
        return maxFiftiesScorer
    }

    fun getTeamRankings(): List<TeamScore> {
        return try {
            teamScores.values.stream()
                .sorted(Comparator.comparingInt(TeamScore::getPointss).reversed()
                    .thenComparingInt(TeamScore::getWinss).reversed())
                .collect(Collectors.toList())
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    fun getSemifinalists(): List<Team> {
        val teamRankings: List<TeamScore>
        val semifinalists: MutableList<Team> = ArrayList()
        try {
            teamRankings = getTeamRankings()
            for (i in teamRankings.size - 1 downTo maxOf(0, teamRankings.size - 2)) {
                val teamScore = teamRankings[i]
                val team = teams.firstOrNull { it.getNames() == teamScore.getTeamNames() }
                if (team != null) {
                    semifinalists.add(team)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return semifinalists
    }

    fun getTopRunScorers(): List<Player> {
        return try {
            val players = playerStats.values.toList()
            players.sortedByDescending { it.getRunsScored() }.take(5)
        } catch (e: NullPointerException) {
            println("Error: Null value encountered while retrieving top run scorers.")
            emptyList()
        } catch (e: Exception) {
            println("Error: An unexpected error occurred while retrieving top run scorers.")
            e.printStackTrace()
            emptyList()
        }
    }

    fun showScoreboard() {
        try {
            println("TEAM RANKINGS:")
            println("-----------------------------------------------")
            println("%-5s %-20s %-10s %-10s".format("Rank", "Team Name", "Wins", "Points"))
            println("-----------------------------------------------")
            val teamRankings = getTeamRankings()
            val reversedRankings = teamRankings.reversed()
            var rank = 1
            for (teamScore in reversedRankings) {
                println("%-5d %-20s %-10d %-10d".format(rank, teamScore.getTeamNames(), teamScore.getWinss(), teamScore.getPointss()))
                rank++
            }
            println("-----------------------------------------------")
            println("\nSEMIFINALISTS:")
            println("----------------------")
            val semifinalists = getSemifinalists()
            semifinalists.forEach { println(it.getNames()) }
            println("----------------------")
        } catch (e: NullPointerException) {
            println("Error: Null value encountered while displaying scoreboard.")
        } catch (e: Exception) {
            println("Error: An unexpected error occurred while displaying scoreboard.")
            e.printStackTrace()
        }
    }
}

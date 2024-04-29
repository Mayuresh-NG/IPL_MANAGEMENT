package ipl_manager_kotlin

/**
 * TeamScore class representing the score and ranking of a team.
 */
class TeamScore(val teamName: String) {
    var wins: Int = 0
    var points: Int = 0

    fun getTeamNames(): String {
        return teamName
    }

    fun getWinss(): Int {
        return wins
    }

    fun getPointss(): Int {
        return points
    }

    fun incrementWins() {
        wins++
    }

    fun addPoints(pointsToAdd: Int) {
        points += pointsToAdd
    }
}

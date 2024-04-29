package ipl_manager_kotlin

class Match(private val team1: Team, private val team2: Team) {
    private val team1PlayerStats: MutableMap<Player, Int> = HashMap()
    private val team2PlayerStats: MutableMap<Player, Int> = HashMap()

    fun updatePlayerStats(player: Player, runsScored: Int, wicketsTaken: Int) {
        if (team1.players.contains(player)) {
            team1PlayerStats[player] = runsScored
            team1PlayerStats[player] = wicketsTaken
        } else if (team2.players.contains(player)) {
            team2PlayerStats[player] = runsScored
            team2PlayerStats[player] = wicketsTaken
        }
    }
}

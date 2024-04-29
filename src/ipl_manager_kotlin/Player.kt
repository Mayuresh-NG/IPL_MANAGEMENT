package ipl_manager_kotlin

/**
 * Player class representing a cricket player.
 */
class Player(val name: String, private var runsScored: Int, private var wicketsTaken: Int) {
    private var fiftiesCount: Int = 0

    @JvmName("getPlayerName")
    fun getName(): String {
        return name
    }

    fun getRunsScored(): Int {
        return runsScored
    }

    fun getWicketsTaken(): Int {
        return wicketsTaken
    }

    fun getFiftiesCount(): Int {
        return fiftiesCount
    }

    fun addRunsScored(runs: Int) {
        runsScored += runs
    }

    fun addWicketsTaken(wickets: Int) {
        wicketsTaken += wickets
    }

    fun incrementFiftiesCount() {
        fiftiesCount++
    }
}

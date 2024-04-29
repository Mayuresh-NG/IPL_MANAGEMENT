package ipl_manager_kotlin

import java.util.*

fun main() {
    // Create a new tournament
    val ipl = Tournament(MatchFormat.IPL)

    // Add teams
    val team1Players: MutableList<Player> = ArrayList()
    team1Players.add(Player("Rohit Sharma", 0, 0))
    team1Players.add(Player("Hardik Pandya", 0, 0))

    ipl.addTeam("MI", "Wankhede Stadium", team1Players)

    val team2Players: MutableList<Player> = ArrayList()
    team2Players.add(Player("Shubhman Gill", 0, 0))
    team2Players.add(Player("Kane Williamson", 0, 0))

    ipl.addTeam("GT", "Narendra Modi Stadium", team2Players)

    val team3Players: MutableList<Player> = ArrayList()
    team3Players.add(Player("M.S Dhoni", 0, 0))
    team3Players.add(Player("Ruturaj Gaikwad", 0, 0))

    ipl.addTeam("CSK", "M. A. Chidambaram Stadium", team3Players)

    val team4Players: MutableList<Player> = ArrayList()
    team4Players.add(Player("Sanju Samson", 0, 0))
    team4Players.add(Player("Yahashwi Jaiswal", 0, 0))
    team4Players.add(Player("Riyan Parag", 0, 0))

    ipl.addTeam("RR", "Sawai Mansingh Stadium", team4Players)

    //get team data
    val team1: Team = ipl.teams[0]
    val team2: Team = ipl.teams[1]
    val team3: Team = ipl.teams[2]
    val team4: Team = ipl.teams[3]

    // Add matches eg : Match 1
    val match1 = Match(team1, team2)
    ipl.addMatch(team1, team2, team1)

    // Update player stats for a match
    match1.updatePlayerStats(team1Players[0], 50, 2)
    match1.updatePlayerStats(team2Players[0], 38, 3)

    ipl.updatePlayerStats(team1Players[0].name, 50, 2)
    ipl.updatePlayerStats(team2Players[0].name, 38, 3)

    // Add matches eg : Match 2
    val match2 = Match(team1, team3)
    ipl.addMatch(team1, team3, null)

    // Update player stats for a match
    match2.updatePlayerStats(team1Players[0], 52, 0)
    match2.updatePlayerStats(team3Players[1], 30, 4)

    ipl.updatePlayerStats(team1Players[0].name, 52, 0)
    ipl.updatePlayerStats(team3Players[1].name, 30, 4)


    println("\nPlayer with Maximum 50s: ${ipl.getMaxFiftiesScorer()?.getName()}")
    println("Number of 50s: ${ipl.getMaxFiftiesScorer()?.getFiftiesCount()}")

    println("\nHighest Wicket Taker: ${ipl.getHighestWicketTaker()?.getName()}")
    println("Wickets: ${ipl.getHighestWicketTaker()?.getWicketsTaken()}")

    println("\nTop 5 Run Scorers:")
    val topRunScorers = ipl.getTopRunScorers()
    topRunScorers.take(5).forEach { println("%-20s %d".format(it.getName(), it.getRunsScored())) }

    println("\nScoreboard:")
    ipl.showScoreboard()
}

package imporves_ipl;

import java.util.ArrayList;
import java.util.List;

class MainClass {
    public static void main(String[] args) {
        Tournament ipl = new Tournament();
        setupTeams(ipl);
        conductMatches(ipl);
        displayTournamentStatistics(ipl);
    }

    private static void setupTeams(Tournament ipl) {
        // Define players for each team
        List<Player> team1Players = List.of(
                new Player("Rohit Sharma", 0, 0),
                new Player("Hardik Pandya", 0, 0)
        );

        List<Player> team2Players = List.of(
                new Player("Shubhman Gill", 0, 0),
                new Player("Kane Williamson", 0, 0)
        );

        List<Player> team3Players = List.of(
                new Player("M.S Dhoni", 0, 0),
                new Player("Ruturaj Gaikwad", 0, 0)
        );

        // Add teams to the tournament
        ipl.addTeam("MI", "Wankhede Stadium", team1Players);
        ipl.addTeam("GT", "Narendra Modi Stadium", team2Players);
        ipl.addTeam("CSK", "M. A. Chidambaram Stadium", team3Players);
    }

    private static void conductMatches(Tournament ipl) {
        // Retrieve teams by name to avoid direct index access
        Team team1 = ipl.getTeamByName("MI").orElseThrow();
        Team team2 = ipl.getTeamByName("GT").orElseThrow();
        Team team3 = ipl.getTeamByName("CSK").orElseThrow();

        // Conduct matches
        ipl.addMatch(team1, team2, team1); // Team 1 wins
        ipl.addMatch(team1, team3, null); // Draw

        // Update player stats (now directly through Tournament to avoid redundancy)
        ipl.updatePlayerStats("Rohit Sharma", 102, 2); // Example stats
        ipl.updatePlayerStats("M.S Dhoni", 70, 0);
    }

    private static void displayTournamentStatistics(Tournament ipl) {
        // Display various statistics
        System.out.println("-----------------------------------------------");
        System.out.println("Tournament Statistics:");
        System.out.println("-----------------------------------------------");

        displayTopPerformers(ipl);
        ipl.showScoreboard();
    }

    private static void displayTopPerformers(Tournament ipl) {
        Player maxFiftiesScorer = ipl.getMaxFiftiesScorer();
        System.out.println("Player with Maximum 50s: " + maxFiftiesScorer.getName() + ", 50s: " + maxFiftiesScorer.getFiftiesCount());

        Player highestWicketTaker = ipl.getHighestWicketTaker();
        System.out.println("Highest Wicket Taker: " + highestWicketTaker.getName() + ", Wickets: " + highestWicketTaker.getWicketsTaken());

        System.out.println("Top 5 Run Scorers:");
        ipl.getTopRunScorers().forEach(player ->
                System.out.printf("%-20s %d\n", player.getName(), player.getRunsScored())
        );
    }
}
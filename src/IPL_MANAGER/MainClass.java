package IPL_MANAGER;

import java.util.ArrayList;
import java.util.List;

class TournamentStatsThread extends Thread {
    private final Tournament match_type;

    public TournamentStatsThread(Tournament match_type) {
        this.match_type = match_type;
    }

    @Override
    public void run() {
        try {
            // Get the player with maximum 50s
            Player maxFiftiesScorer = match_type.getMaxFiftiesScorer();
            System.out.println("-----------------------------------------------");
            System.out.println("Player with Maximum 50s: " + maxFiftiesScorer.getName());
            System.out.println("Number of 50s: " + maxFiftiesScorer.getFiftiesCount());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class HighestWicketTakerThread extends Thread {
    private final Tournament match_type;

    public HighestWicketTakerThread(Tournament match_type) {
        this.match_type = match_type;
    }

    @Override
    public void run() {
        try {
            // Get the highest wicket-taker
            Player highestWicketTaker = match_type.getHighestWicketTaker();
            System.out.println("Highest Wicket Taker: " + highestWicketTaker.getName());
            System.out.println("Wickets: " + highestWicketTaker.getWicketsTaken());
            System.out.println("-----------------------------------------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class ScoreBoardThread extends Thread {
    private final Tournament match_type;

    public ScoreBoardThread(Tournament match_type) {
        this.match_type = match_type;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n");
            match_type.showScoreboard();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class TopRunScorersThread extends Thread {
    private final Tournament match_type;

    public TopRunScorersThread(Tournament match_type) {
        this.match_type = match_type;
    }

    @Override
    public void run() {
        try {
            System.out.println("\n");
            List<Player> topRunScorers = match_type.getTopRunScorers();
            System.out.println("Top 5 Run Scorers:");
            for (Player player : topRunScorers) {
                System.out.printf("%-20s %d\n", player.getName(), player.getRunsScored());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class MainClass {
    public static void main(String[] args) {
        // Create a new tournament
        Tournament ipl = new Tournament(MatchFormat.IPL);
        List<Thread> threads = new ArrayList<>();

        // Add teams
        List<Player> team1Players = new ArrayList<>();
        team1Players.add(new Player("Rohit sharma", 0, 0));
        team1Players.add(new Player("Hardik Pandya", 0, 0));

        ipl.addTeam("MI", "Wankhede Stadium", team1Players);

        List<Player> team2Players = new ArrayList<>();
        team2Players.add(new Player("Shubhman Gills", 0, 0));
        team2Players.add(new Player("Kane Williamson", 0, 0));

        ipl.addTeam("GT", "Narendra Modi Stadium", team2Players);

        List<Player> team3Players = new ArrayList<>();
        team3Players.add(new Player("M.S Dhoni", 0, 0));
        team3Players.add(new Player("Ruturaj Gaikwad", 0, 0));

        ipl.addTeam("CSK", "M. A. Chidambaram Stadium", team3Players);

        List<Player> team4Players = new ArrayList<>();
        team3Players.add(new Player("Sanju Samson", 0, 0));
        team3Players.add(new Player("Yahashwi Jaiswal", 0, 0));
        team3Players.add(new Player("Riyan Parag", 0, 0));

        ipl.addTeam("RR", "Sawai Mansingh Stadium", team4Players);

        //get team data
        Team team1 = ipl.getTeams().get(0);
        Team team2 = ipl.getTeams().get(1);
        Team team3 = ipl.getTeams().get(2);
        Team team4 = ipl.getTeams().get(3);

        // Add matches eg : Match 1
        Match match1 = new Match(team1, team2);
        ipl.addMatch(team1, team2 , team1);

        // Update player stats for a match
        match1.updatePlayerStats(team1Players.get(0), 50, 2);
        match1.updatePlayerStats(team2Players.get(0), 38, 3);

        ipl.updatePlayerStats(team1Players.get(0).getName(), 50, 2);
        ipl.updatePlayerStats(team2Players.get(0).getName(), 38, 3);

        // Add matches eg : Match 2
        Match match2 = new Match(team1, team3);
        ipl.addMatch(team1, team3,null);

        // Update player stats for a match
        match2.updatePlayerStats(team1Players.get(0), 52, 0);
        match2.updatePlayerStats(team3Players.get(1), 30, 4);

        ipl.updatePlayerStats(team1Players.get(0).getName(), 52, 0);
        ipl.updatePlayerStats(team3Players.get(1).getName(), 30, 4);


        threads.add(new TournamentStatsThread(ipl));
        threads.add(new HighestWicketTakerThread(ipl));
        threads.add(new ScoreBoardThread(ipl));
        threads.add(new TopRunScorersThread(ipl));

        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join(); // Wait for the thread to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
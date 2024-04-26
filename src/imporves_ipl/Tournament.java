package imporves_ipl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tournament class representing the entire cricket tournament.
 */
public class Tournament {
    private final List<Team> teams;
    private final List<Match> matches;
    private final Map<String, Player> playerStats;
    private final Map<String, TeamScore> teamScores;

    public Tournament() {
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        playerStats = new HashMap<>();
        teamScores = new HashMap<>();
    }

    public Optional<Team> getTeamByName(String name) {
        return teams.stream()
                .filter(team -> team.getName().equals(name))
                .findFirst();
    }

    public void addTeam(String name, String homeGround, List<Player> players) {
        Team team = new Team(name, homeGround, players);
        teams.add(team);
        teamScores.put(name, new TeamScore(name));
        for (Player player : players) {
            playerStats.put(player.getName(), player);
        }
    }

    public void addMatch(Team team1, Team team2, Team winner) {
        Match match = new Match(team1, team2);
        matches.add(match);
        updateTeamScores(winner, team1, team2);
    }

    private void updateTeamScores(Team winner, Team team1, Team team2) {
        if (winner != null) {
            TeamScore winnerScore = teamScores.get(winner.getName());
            winnerScore.incrementWins();
            winnerScore.addPoints(2);
        } else {
            teamScores.get(team1.getName()).addPoints(1);
            teamScores.get(team2.getName()).addPoints(1);
        }
    }

    public void updatePlayerStats(String playerName, int runsScored, int wicketsTaken) {
        Player player = playerStats.get(playerName);
        if (player == null) {
            throw new IllegalArgumentException("Player not found in tournament.");
        }
        player.addRunsScored(runsScored);
        player.addWicketsTaken(wicketsTaken);
    }

    public Player getHighestWicketTaker() {
        return playerStats.values().stream()
                .max(Comparator.comparingInt(Player::getWicketsTaken))
                .orElseThrow(() -> new IllegalStateException("No players found in tournament."));
    }

    public Player getMaxFiftiesScorer() {
        return playerStats.values().stream()
                .max(Comparator.comparingInt(Player::getFiftiesCount))
                .orElseThrow(() -> new IllegalStateException("No players found in tournament."));
    }

    public List<Player> getTopRunScorers() {
        return playerStats.values().stream()
                .sorted(Comparator.comparingInt(Player::getRunsScored).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public void showScoreboard() {
        List<TeamScore> sortedScores = new ArrayList<>(teamScores.values());
        sortedScores.sort(Comparator.comparingInt(TeamScore::getPoints).reversed()
                .thenComparingInt(TeamScore::getWins).reversed());

        System.out.println("TEAM RANKINGS:");
        System.out.println("-----------------------------------------------");
        int rank = 1;
        for (TeamScore score : sortedScores) {
            System.out.printf("%-5d %-20s %-10d %-10d\n", rank++, score.getTeamName(), score.getWins(), score.getPoints());
        }
        System.out.println("-----------------------------------------------");
    }

    // Additional methods like getters for teams, matches, and player stats can be added here
    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Map<String, Player> getPlayerStats() {
        return playerStats;
    }

    public Map<String, TeamScore> getTeamScores() {
        return teamScores;
    }
}

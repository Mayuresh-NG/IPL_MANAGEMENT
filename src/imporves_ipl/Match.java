package imporves_ipl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Match class representing a cricket match.
 */
class Match {
    private final Team team1;
    private final Team team2;
    private final Map<Player, PlayerStats> team1PlayerStats;
    private final Map<Player, PlayerStats> team2PlayerStats;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        team1PlayerStats = new HashMap<>();
        team2PlayerStats = new HashMap<>();
    }

    public void updatePlayerStats(Player player, int runsScored, int wicketsTaken) {
        Map<Player, PlayerStats> relevantMap = team1.getPlayers().contains(player) ? team1PlayerStats : team2.getPlayers().contains(player) ? team2PlayerStats : null;

        if (relevantMap == null) {
            throw new IllegalArgumentException("Player does not belong to either team in this match.");
        }

        PlayerStats stats = relevantMap.getOrDefault(player, new PlayerStats());
        stats.addRuns(runsScored);
        stats.addWickets(wicketsTaken);
        relevantMap.put(player, stats);
    }

    // Additional methods like getters for team stats can be added here
}

class PlayerStats {
    private int runs;
    private int wickets;

    public void addRuns(int runs) {
        this.runs += runs;
    }

    public void addWickets(int wickets) {
        this.wickets += wickets;
    }

    // Getters
    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }
}


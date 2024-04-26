package IPL_MANAGER;

import java.util.HashMap;
import java.util.Map;

class Match {
    private final Team team1;
    private final Team team2;
    private final Map<Player, Integer> team1PlayerStats;
    private final Map<Player, Integer> team2PlayerStats;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        team1PlayerStats = new HashMap<>();
        team2PlayerStats = new HashMap<>();
    }

    public void updatePlayerStats(Player player, int runsScored, int wicketsTaken) {
        if (team1.getPlayers().contains(player)) {
            team1PlayerStats.put(player, runsScored);
            team1PlayerStats.put(player, wicketsTaken);
        } else if (team2.getPlayers().contains(player)) {
            team2PlayerStats.put(player, runsScored);
            team2PlayerStats.put(player, wicketsTaken);
        }
    }

    public Map<Player, Integer> getTeam1PlayerStats() {
        return new HashMap<>(team1PlayerStats);
    }

    public Map<Player, Integer> getTeam2PlayerStats() {
        return new HashMap<>(team2PlayerStats);
    }
}

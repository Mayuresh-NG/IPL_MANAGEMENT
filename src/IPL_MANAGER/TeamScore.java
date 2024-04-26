package IPL_MANAGER;

/**
 * TeamScore class representing the score and ranking of a team.
 */
class TeamScore {
    private final String teamName;
    private int wins;
    private int points;

    public TeamScore(String teamName) {
        this.teamName = teamName;
        this.wins = 0;
        this.points = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWins() {
        return wins;
    }

    public int getPoints() {
        return points;
    }

    public void incrementWins() {
        wins++;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}

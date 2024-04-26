package imporves_ipl;
/**
 * TeamScore class representing the score and ranking of a team.
 */
class TeamScore {
    private final String teamName;
    private int wins;
    private int points;

    public TeamScore(String teamName) {
        if (teamName == null || teamName.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty.");
        }
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

    public void addPoints(int pointsToAdd) {
        if (pointsToAdd < 0) {
            throw new IllegalArgumentException("Points to add cannot be negative.");
        }
        this.points += pointsToAdd;
    }

    // Optional: Method to reset scores, useful for a new season or resetting statistics
    public void resetScores() {
        this.wins = 0;
        this.points = 0;
    }
}

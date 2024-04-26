package imporves_ipl;


/**
 * Player class representing a cricket player.
 */
class Player {
    private final String name;
    private int runsScored;
    private int wicketsTaken;
    private int fiftiesCount;

    public Player(String name, int runsScored, int wicketsTaken) {
        this.name = name;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.fiftiesCount = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public int getFiftiesCount() {
        return fiftiesCount;
    }

    // Setters
    public void addRunsScored(int runs) {
        if (runs < 0) {
            throw new IllegalArgumentException("Runs scored cannot be negative.");
        }
        runsScored += runs;
        checkAndIncrementFifties(runs);
    }

    public void addWicketsTaken(int wickets) {
        if (wickets < 0) {
            throw new IllegalArgumentException("Wickets taken cannot be negative.");
        }
        wicketsTaken += wickets;
    }

    // Private helper method
    private void checkAndIncrementFifties(int runs) {
        if (runs >= 50 && runs < 100) { // Assuming a fifty is scored when runs are between 50 and 99
            incrementFiftiesCount();
        }
    }

    private void incrementFiftiesCount() {
        fiftiesCount++;
    }
}

package IPL_MANAGER;

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

    public void addRunsScored(int runs) {
        runsScored += runs;
    }

    public void addWicketsTaken(int wickets) {
        wicketsTaken += wickets;
    }

    public void incrementFiftiesCount() {
        fiftiesCount++;
    }
}

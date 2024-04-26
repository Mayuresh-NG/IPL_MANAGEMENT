package IPL_MANAGER;

import java.util.List;

/**
 * Team class representing a cricket team.
 * @param <P> Type parameter representing the type of players in the team.
 */
class Team<P extends Player> {
    private final String name;
    private final String homeGround;
    private final List<P> players;

    public Team(String name, String homeGround, List<P> players) {
        this.name = name;
        this.homeGround = homeGround;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public List<P> getPlayers() {
        return players;
    }
}

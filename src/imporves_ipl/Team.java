package imporves_ipl;

import java.util.Collections;
import java.util.List;

/**
 * Team class representing a cricket team.
 */
class Team {
    private final String name;
    private final String homeGround;
    private final List<Player> players;

    public Team(String name, String homeGround, List<Player> players) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty.");
        }
        if (homeGround == null || homeGround.trim().isEmpty()) {
            throw new IllegalArgumentException("Home ground cannot be null or empty.");
        }
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Players list cannot be null or empty.");
        }
        this.name = name;
        this.homeGround = homeGround;
        this.players = Collections.unmodifiableList(players); // Make the list unmodifiable to ensure immutability
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // Additional functionality can be added here, such as methods to add or remove a player, if the design permits mutable teams
}

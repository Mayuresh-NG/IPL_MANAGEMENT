package IPL_MANAGER;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tournament class representing the entire cricket tournament.
 */
class Tournament {
    private final List<Team> teams;
    private final List<Match> matches;
    private final Map<String, Player> playerStats;
    private final Map<String, TeamScore> teamScores;

    private final MatchFormat matchFormat;

    public Tournament(MatchFormat matchFormat) {
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        playerStats = new HashMap<>();
        teamScores = new HashMap<>();

        this.matchFormat = matchFormat;
    }

    public void addTeam(String name, String homeGround, List<Player> players) {
        int maxPlayersPerTeam = (matchFormat == MatchFormat.IPL) ? 12 : 11;
        if (players.size() <= maxPlayersPerTeam) {
            Team team = new Team(name, homeGround, players);
            teams.add(team);
            teamScores.put(name, new TeamScore(name));
            for (Player player : players) {
                playerStats.put(player.getName(), player);
            }
        } else {
            System.out.println("Error: Team " + name + " has more than " + maxPlayersPerTeam + " players.");
        }
    }

    public void addMatch(Team team1, Team team2, Team winner) {
        Match match = new Match(team1, team2);
        matches.add(match);

        if (winner != null) {
            TeamScore winnerScore = teamScores.get(winner.getName());
            winnerScore.incrementWins();
            winnerScore.addPoints(2);
        } else {
            TeamScore team1Score = teamScores.get(team1.getName());
            team1Score.addPoints(1);
            TeamScore team2Score = teamScores.get(team2.getName());
            team2Score.addPoints(1);
        }
    }

    public void updatePlayerStats(String playerName, int runsScored, int wicketsTaken) {
        Player player = playerStats.get(playerName);
        if (player != null) {
            player.addRunsScored(runsScored);
            player.addWicketsTaken(wicketsTaken);
            if (runsScored >= 50) {
                player.incrementFiftiesCount();
            }
        }
    }

    public Player getHighestWicketTaker() {
        Player highestWicketTaker = null;
        int maxWickets = 0;
        for (Player player : playerStats.values()) {
            if (player.getWicketsTaken() > maxWickets) {
                maxWickets = player.getWicketsTaken();
                highestWicketTaker = player;
            }
        }
        return highestWicketTaker;
    }

    public Player getMaxFiftiesScorer() {
        Player maxFiftiesScorer = null;
        int maxFifties = 0;
        for (Player player : playerStats.values()) {
            if (player.getFiftiesCount() > maxFifties) {
                maxFifties = player.getFiftiesCount();
                maxFiftiesScorer = player;
            }
        }
        return maxFiftiesScorer;
    }

    public List<TeamScore> getTeamRankings() {
        try {
            return teamScores.values().stream()
                    .sorted(Comparator.comparingInt(TeamScore::getPoints).reversed()
                            .thenComparingInt(TeamScore::getWins).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Team> getTeams() {
        try {
            if (teams != null) {
                return teams;
            } else {
                throw new IllegalStateException("'teams' is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Team> getSemifinalists() {
        List<TeamScore> teamRankings;
        List<Team> semifinalists = new ArrayList<>();

        try {
            teamRankings = getTeamRankings();

            // Add the top 2 teams to the semifinalists list
            for (int i = teamRankings.size() - 1; i >= teamRankings.size() - 2 && i >= 0; i--) {
                TeamScore teamScore = teamRankings.get(i);
                String teamName = teamScore.getTeamName();
                Team team = teams.stream()
                        .filter(t -> t.getName().equals(teamName))
                        .findFirst()
                        .orElse(null);
                if (team != null) {
                    semifinalists.add(team);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return semifinalists;
    }

    public List<Player> getTopRunScorers() {
        try {
            List<Player> players = new ArrayList<>(playerStats.values());
            players.sort(Comparator.comparingInt(Player::getRunsScored).reversed());
            return players.subList(0, Math.min(players.size(), 5));
        } catch (NullPointerException e) {
            System.out.println("Error: Null value encountered while retrieving top run scorers.");
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred while retrieving top run scorers.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void showScoreboard() {
        try {
            System.out.println("TEAM RANKINGS:");
            System.out.println("-----------------------------------------------");
            System.out.printf("%-5s %-20s %-10s %-10s\n", "Rank", "Team Name", "Wins", "Points");
            System.out.println("-----------------------------------------------");

            List<TeamScore> teamRankings = getTeamRankings();
            List<TeamScore> reversedRankings = new ArrayList<>(teamRankings);
            Collections.reverse(reversedRankings);

            int rank = 1;
            for (TeamScore teamScore : reversedRankings) {
                System.out.printf("%-5d %-20s %-10d %-10d\n", rank, teamScore.getTeamName(), teamScore.getWins(), teamScore.getPoints());
                rank++;
            }

            System.out.println("-----------------------------------------------");
            System.out.println("\nSEMIFINALISTS:");
            System.out.println("----------------------");

            List<Team> semifinalists = getSemifinalists();
            for (Team team : semifinalists) {
                System.out.println(team.getName());
            }

            System.out.println("----------------------");
        } catch (NullPointerException e) {
            System.out.println("Error: Null value encountered while displaying scoreboard.");
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred while displaying scoreboard.");
            e.printStackTrace();
        }
    }
}
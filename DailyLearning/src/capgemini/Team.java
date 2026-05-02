package capgemini;

import java.util.List;

public class Team {

	List<Player> players;

	public Team(List<Player> players) {
		super();
		this.players = players;
	}

	public int getScore() {
		if (players != null) {
			return players.stream().mapToInt(player -> player.getScore())
					.reduce(0, (a, b) -> a + b);
		}
		return 0;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Team [players=" + players + "]";
	}

}

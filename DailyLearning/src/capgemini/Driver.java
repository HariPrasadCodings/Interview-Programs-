package capgemini;

import java.util.Arrays;
import java.util.List;

public class Driver {
	public static void main(String[] args) {

		List<Player> players = Arrays.asList(new Player("hari", 70, "Batsman"),
				new Player("arya", 50, "Batsman"),
				new Player("obulesh", 40, "Batsman"),
				new Player("chinna", 30, "Batsman"),
				new Player("raju", 60, "Batsman"));
		Team srh = new Team(players);

		int totalScore = srh.getScore();
		System.out.println(totalScore);
	}

}

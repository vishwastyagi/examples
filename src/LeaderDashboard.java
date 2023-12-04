import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/*

Design Game Leaderboard class
addPoints(player, point)
top(m)

 */
class GameLeaderboard{


    private final Map<String, Integer> playerPoints;
    private final PriorityQueue<Map.Entry<String, Integer>> topPlayersHeap;

    public GameLeaderboard() {
        playerPoints = new HashMap<>();
        topPlayersHeap = new PriorityQueue<>(Comparator.comparingInt((Map.Entry<String, Integer> entry) -> entry.getValue()).reversed());
    }

    public void addPoints(String player, int points) {
        playerPoints.merge(player, points, Integer::sum);

        // Update the top players heap
        topPlayersHeap.removeIf(entry -> entry.getKey().equals(player));
        topPlayersHeap.add(new AbstractMap.SimpleEntry<>(player, playerPoints.get(player)));
    }
  /*  private final int m;

    public GameLeaderboard(int m) {
        this.m = m;
        playerPoints = new HashMap<>();
        topPlayersHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
    }

    public void addPoints(String player, int points) {
        playerPoints.merge(player, points, Integer::sum);

        // Update the top players heap
        topPlayersHeap.removeIf(entry -> entry.getKey().equals(player));
        topPlayersHeap.add(new AbstractMap.SimpleEntry<>(player, playerPoints.get(player)));

        // Keep the heap size limited to m
        while (topPlayersHeap.size() > m) {
            topPlayersHeap.poll();
        }
    }*/

    public List<Map.Entry<String, Integer>> top(int m) {
        return topPlayersHeap.stream()
                .sorted(Comparator.comparingInt((Map.Entry<String, Integer> entry) -> entry.getValue()).reversed())
                .limit(m)
                .collect(Collectors.toList());
    }



}
public class LeaderDashboard {
    private static final Logger LOGGER = Logger.getLogger( GameLeaderboard.class.getName() );
    public static void main(String[] args) {
        int k = 3;  // replace with your desired value for 'm'
        for(int runningNumber=10;runningNumber<=100000;runningNumber=runningNumber*10) {
            LOGGER.info("runningNumber = "+runningNumber);
            for (int t = 0; t < 5; t++) {
                GameLeaderboard leaderboard = new GameLeaderboard();
                long startTime = System.currentTimeMillis();
               // LOGGER.info(startTime + "");

                for (int i = 1; i <= runningNumber; i++) {
                    leaderboard.addPoints("Player" + i, i);
                  /*  leaderboard.addPoints("Player2", 150);
                    leaderboard.addPoints("Player3", 80);
                    leaderboard.addPoints("Player4", 200);
                    leaderboard.addPoints("Player5", 250);*/
                }
                long topStartTime = System.currentTimeMillis();
                List<Map.Entry<String, Integer>> topPlayers = leaderboard.top(4);
                System.out.println("Top Players: " + topPlayers);
                LOGGER.info("top method time = " + (System.currentTimeMillis() - topStartTime));

                LOGGER.info(("Total Time =" + (System.currentTimeMillis() - startTime)));
            }
        }
      /*  topPlayers = leaderboard.top(2);
        System.out.println("Top Players: " + topPlayers);
        topPlayers = leaderboard.top(2);
        System.out.println("Top Players: " + topPlayers);
        topPlayers = leaderboard.top(2);
        System.out.println("Top Players: " + topPlayers);*/
    }
}

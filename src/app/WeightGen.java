import java.util.*;

public class WeightGen {

	Random rand = new Random();
	private int weight;
	public WeightGen() {
	
	}
	
	public int Weight() {
		weight = rand.nextInt(4000)+26000;//kg!!!
		return weight;
	}
	
	public String toString() {
		return Weight() +"";
	}
	
}

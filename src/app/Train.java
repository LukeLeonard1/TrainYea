import java.util.ArrayList;

public class Train {
	
	String direction, line;
	
	ArrayList<TrainCars> trains = new ArrayList<TrainCars>();
	public Train() {
	
	}
	
	public Train(int TrainCars, String bound, String color) {
		
		for(int i=1; i<= TrainCars; i++) {
			trains.add(new TrainCars(i));
		}

		direction = bound;
		line = color;
	}

	public String toString() {
		return direction+ " "+ line+" ";
	}
}

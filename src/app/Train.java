package app;

import java.util.ArrayList;

public class Train {

	int currentCar;
	String direction, line;

	// TrainCars trainCar = new TrainCars();
	ArrayList<TrainCars> trains = new ArrayList<TrainCars>();

	public Train() {

	}

	public Train(int TrainCars, String bound, String color) {

		for (int i = 1; i <= TrainCars; i++) {
			trains.add(new TrainCars(i));
			trains.get(i - 1).setWeight();
		}
		currentCar = TrainCars;
		direction = bound;
		line = color;
	}

	public String toString() {
		String temp = "";
		for (int i = 1; i <= trains.size(); i++) {
			temp += " Cart " + i + " (" + trains.get(i - 1).seatRange() + ")" + "   (Random used :"
					+ Math.floor((trains.get(i - 1).getCurrentCapacity() * trains.get(i - 1).KG_PER_PERSON
							+ trains.get(i - 1).TRAIN_WEIGHT))
					+ ")";// cart range
		}
		return direction + " " + line + temp + " ";
	}
}

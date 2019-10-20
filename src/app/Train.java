package app;

import java.util.ArrayList;

public class Train {

	int numOfCars;
	int ID = 0;
	String direction, line;

	ArrayList<TrainCars> cars = new ArrayList<TrainCars>();

	public Train(int TrainCars, String bound, String color, int ID) {
		this.ID = ID;
		for (int i = 1; i <= TrainCars; i++) {
			cars.add(new TrainCars(i));
			cars.get(i - 1).setWeight();
		}
		numOfCars = TrainCars;
		direction = bound;
		line = color;
	}

	public String toString() {
		String temp = "";
		for (int i = 1; i <= cars.size(); i++) {
			temp += " Cart " + i + " (" + cars.get(i - 1).seatRange() + ")" + "   (Random used :" + Math.floor(
					(cars.get(i - 1).getCurrentPop() * cars.get(i - 1).KG_PER_PERSON + cars.get(i - 1).TRAIN_WEIGHT))
					+ ")";// cart range
		}
		return direction + " " + line + temp + " ";
	}

	public String getDirection() {
		return direction;
	}

	public ArrayList<TrainCars> getCars() {
		return cars;
	}

	public void setWeights() {
		for (TrainCars trainCars : cars) {
			trainCars.setWeight();
		}
	}

	public int getID() { // TODO: remove after calling static db
		return ID;
	}

	public String getLine() {
		return line;
	}

	public ArrayList<TrainCars> getTrains() {
		return cars;
	}

}

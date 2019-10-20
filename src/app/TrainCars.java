package app;

public class TrainCars {

	private int carID;

	private int currentPop;

	final int MAX_CAPACITY = 61, MAX_SEATS = 38, TRAIN_WEIGHT = 26000;// kg

	final double KG_PER_PERSON = 82.6;

	public TrainCars(int NewCarID) {
		carID = NewCarID;
	}

	public void calcCurrentCapacity(double currentWeight) {

		currentPop = (int) ((currentWeight - TRAIN_WEIGHT) / (KG_PER_PERSON));

	}

	public void setWeight() {
		WeightGen gen = new WeightGen();
		this.calcCurrentCapacity(gen.Weight());
	}

	public int seatRange() {
		if (currentPop >= 0 && currentPop <= 43) {

			if (currentPop <= 19)
				return 1;// several seats avaliable
			else if (currentPop <= 29)
				return 2;// some seats avaliable
			else if (currentPop <= 38)
				return 3;// Possiblly avaliable
		}
		return 4; // no seats avaliable

	}

	public String toString() {
		return "" + seatRange();

	}

	public int getCarID() {
		return carID;
	}

	public int getCurrentPop() {
		return currentPop;
	}

}

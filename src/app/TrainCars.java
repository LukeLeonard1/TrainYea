package app;

public class TrainCars {
	
	int currentTotalWeight,
		carID;
	
	double currentCap;
	
	String line, direction;
	
	final int MAX_CAPACITY=61,
			  MAX_SEATS=38,
			  TRAIN_WEIGHT=26000;//kg
	
	final double KG_PER_PERSON = 82.6;
			  
	public TrainCars(int NewCarID) {
		carID=NewCarID;
	}
	

	public double calcCurrentCapacity(double currentWeight) {
	
		currentCap =(currentWeight-TRAIN_WEIGHT)/(KG_PER_PERSON);
		return Math.floor(currentCap);
		
	}
	
	public double getCurrentCapacity() {
		return currentCap;
	}
	
	public int getCurrentCart(int counter) {
		return  - counter;
	}
	
	public int seatRange() {
		if(getCurrentCapacity() >= 0 && getCurrentCapacity() <=43) {
			
			if(getCurrentCapacity() <=19)
				return 1;//several seats avaliable
			else if(getCurrentCapacity() <=29)
				return 2;//some seats avaliable
			else if(getCurrentCapacity() <=38)
				return 3;// Possiblly avaliable
			else
				return 4; //no seats avaliable
		}
		else
			return 5;// Ton of people on that cart
	
	}

	public String toString() {
		return direction + " " + line + " " +seatRange();
		
	}
}


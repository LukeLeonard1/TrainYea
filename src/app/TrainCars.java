package app;

public class TrainCars {
	
	int currentTotalWeight,
		carID;
	
	double currentCap;
	
	final int MAX_CAPACITY=61,
			  MAX_SEATS=38,
			  TRAIN_WEIGHT=26000;//kg
	
	final double KG_PER_PERSON = 82.6;
			  
	public TrainCars(int NewCarID) {
		carID=NewCarID;
	}
	

	public void calcCurrentCapacity(double currentWeight) {
	
		currentCap =(currentWeight-TRAIN_WEIGHT)/(KG_PER_PERSON);
		
	}
	
	public double getCurrentCapacity() {
		return currentCap;
	}
	
	public void setWeight() {
		WeightGen gen = new WeightGen();
		this.calcCurrentCapacity(gen.Weight());
	}
	
	public int seatRange() {
		if(getCurrentCapacity() >= 0 && getCurrentCapacity() <=43) {
			
			if(getCurrentCapacity() <=19)
				return 1;//several seats avaliable
			else if(getCurrentCapacity() <=29)
				return 2;//some seats avaliable
			else if(getCurrentCapacity() <=38)
				return 3;// Possiblly avaliable
		}
		return 4; //no seats avaliable
	
	}

	public String toString() {
		return  ""+seatRange();
		
	}
}


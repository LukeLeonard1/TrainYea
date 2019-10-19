
public class CTATrainCarClient {

	public static void main(String[]args) {
		
		double trainWeight = 26200; //Given by scale
		int numberOfCarts = 3;//Given by scale
		int trainID = 1;//Given by scale
		
		String color = "Red Line";
		String direction = "North";
		
		int counter = numberOfCarts;
	
		while (counter > 0) {
			
		CTATrainCar train = new CTATrainCar(trainID,counter,color, direction);
		
		train.calcCurrentCapacity(trainWeight); //get cart weight
		train.seatRange(); //Print out 0-5
		train.getCurrentCart(counter);
		
		System.out.println(train);
		
		counter--;
		}
	}

}

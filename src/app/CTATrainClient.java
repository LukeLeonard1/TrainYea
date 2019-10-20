package app;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CTATrainClient {

	private static final String url = "jdbc:mysql://localhost:3307/TrainYea?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static TrainSQL dbs = new TrainSQL(url, "root", "");
	private static ArrayList<Train> trains = new ArrayList<Train>();

	private static void setTrainWeights() {
		for (Train train : trains) {
			train.setWeights();
		}
	}

	private static void init() {
		int tempID = 0;
		String[] colors = { "Pink ", "Green ", "Blue ", "Red " };
		Train temp;
		for (String trainLine : colors) {
			tempID++;
			temp = new Train(5, "North", trainLine, tempID);
			System.out.println(temp);
			trains.add(temp);
			tempID++;
			temp = new Train(5, "South", trainLine, tempID);
			System.out.println(temp);
			trains.add(temp);
		}
		setTrainWeights();
	}

	public static void main(String[] args) throws InterruptedException {
		init();
		while (true) {
			TimeUnit.SECONDS.sleep(5);
			for (Train train : trains) {
				// dbs.pullTrain(train.getLine(), train.getDirection()); //FIXME: pull static
				// data from db
				dbs.setTrainID(train.getID());
				for (TrainCars car : train.getCars()) {
					car.setWeight();
					dbs.pushCar(car.getCarID(), car.getCurrentPop());
				}
			}
		}
	}

}

package app;

import java.util.ArrayList;

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
		String[] colors = { "Red ", "Blue ", "Green ", "Pink " };
		Train temp;
		for (String trainLine : colors) {
			temp = new Train(5, "North", trainLine);
			System.out.println(temp);
			trains.add(temp);
			temp = new Train(5, "South", trainLine);
			System.out.println(temp);
			trains.add(temp);
		}
		setTrainWeights();
	}

	public static void main(String[] args) {
		init();
		while (true) {
			for (Train train : trains) {
				dbs.pullTrain(train.getLine(), train.getDirection());
				for (TrainCars car : train.getCars()) {
					dbs.pushCar(car.getCarID(), car.getCurrentPop());	
				}
			}
		}
	}

}

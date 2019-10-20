package app;

public class CTATrainClient {

	public static void main(String[] args) {

		// ArrayList<TrainCars> trains = new ArrayList<TrainCars>();

		// WeightGen gen = new WeightGen();
		// Train sRLine = new Train(5, "South", "Red ");
		// System.out.println(sRLine);
		// Train nRLine = new Train(5, "North", "Red ");
		// System.out.println(nRLine);

		// Train sBLine = new Train(5, "South", "Blue");
		// System.out.println(sBLine);
		// Train nBLine = new Train(5, "North", "Blue");
		// System.out.println(nBLine);

		// Train sGLine = new Train(5, "South", "Green");
		// System.out.println(sGLine);
		// Train nGLine = new Train(5, "North", "Green");
		// System.out.println(nGLine);

		// Train sPLine = new Train(5, "South", "Pink");
		// System.out.println(sPLine);
		// Train nPLine = new Train(5, "North", "Pink");
		// System.out.println(nPLine);

		TrainSQL dbs = new TrainSQL("jdbc:mysql://localhost:3306", "root", "password");
		
		dbs.pushCar(2, 2);
		dbs.pushCar(1, 4);

	}

}

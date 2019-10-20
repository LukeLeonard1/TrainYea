package app;

public class TrainSQL {
    // private static final String TRAINTABLE = "Train"; //Used for calling
    private static final String TRAINLINE = "TrainLine";
    private static final String DIRECTION = "Direction";
    private static final String CURRENTTABLE = "Current";
    private static final String RANGE = "Rng";
    private static final String CART = "Cart";
    private static final String ID = "TrainId";
    private int trainID = -1;
    private String trainFilter = "";

    SQLConnection dbs;

    public void setTrainID(int trainID) {
        this.trainID = trainID;
        trainFilter = ID + "=" + trainID;
        System.out.println("Train ID: " + trainID + " set!");
    }

    private boolean IDSet() {
        if (trainID == -1) {
            System.out.println("Train ID not set!");
            return false;
        }
        return true;
    }

    public void pullTrain(String color, String direction) {
        Object obj = dbs.checkElement(CURRENTTABLE, TRAINLINE + "=" + color + ", " + DIRECTION + "=" + direction,
                trainFilter);
        if (obj instanceof Integer)
            setTrainID((Integer) obj);
    }

    public void pushCar(int carNum, int range) {
        if (IDSet()) {
            String fnlFilter = trainFilter + " AND " + CART + "=" + carNum;
            String defaultElm = ID + ", " + CART;
            String defaultVal = trainID + "," + carNum;
            dbs.update_query(CURRENTTABLE, RANGE, "" + range, fnlFilter, defaultElm, defaultVal);
        } else {
            System.out.println("Failed to push car!");
        }
    }

    public TrainSQL(String url, String root, String password) {
        dbs = new SQLConnection(url, root, password);
    }
}
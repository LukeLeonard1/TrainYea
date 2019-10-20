package app;

public class TrainSQL {
    private static final String TRAINTABLE = "Train";
    private static final String CURRENTTABLE = "Current";
    private static final String TRAINLINE = "TrainLine";
    private static final String DIRECTION = "Direction";
    private static final String RANGE = "Range";
    private static final String CART = "Cart";
    private static final String ID = "Id";
    private short trainID = 1;
    private String trainFilter = "";
    private boolean connected = false;

    SQLConnection dbs = new SQLConnection();

    public void setTrainID(short trainID) {
        this.trainID = trainID;
        trainFilter = ID + " = " + trainID;
        System.out.println("Train ID: " + trainID + " set!");
    }

    private boolean IDSet() {
        if (trainID != -1) {
            System.out.println("Train ID not set!");
            return false;
        }
        return true && connected;
    }

    public void pushTrain(String color, char direction) {
        if (IDSet()) {
            dbs.update_query(TRAINTABLE, TRAINLINE + "=" + color + ", " + DIRECTION + "=" + direction, trainFilter);
        }
    }

    public void pushCar(short carID, byte range) {
        if (IDSet()) {
            String fnlFilter = trainFilter + " ," + CART + "=" + carID;
            dbs.update_query(CURRENTTABLE, RANGE + "=" + range, fnlFilter);
        }
    }

    public void connect(String url, String root, String password) {
        connected = dbs.establishConnection(url, root, password);
        System.out.println(connected ? "Connection Made!" : "Connection Failed!");
    }
}
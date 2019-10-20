package app;

public class TrainSQL {
    // private static final String TRAINTABLE = "Train"; //Used for calling
    // private static final String TRAINLINE = "TrainLine";
    // private static final String DIRECTION = "Direction";
    private static final String CURRENTTABLE = "Current";
    private static final String RANGE = "Range";
    private static final String CART = "Cart";
    private static final String ID = "Id";
    private int trainID = 1;
    private String trainFilter = "";
    private boolean connected = false;

    SQLConnection dbs = new SQLConnection();

    public void setTrainID(int trainID) {
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

    // public void pushTrain(String color, char direction) {
    //     if (IDSet()) {
    //         dbs.update_query(CURRENTTABLE, TRAINLINE + "=" + color + ", " + DIRECTION + "=" + direction, trainFilter);
    //     }
    // }

    public void pushCar(int carNum, int range) {
        if (IDSet()) {
            String fnlFilter = trainFilter + " ," + CART + "=" + carNum;
            dbs.update_query(CURRENTTABLE, CART, "" + range, fnlFilter);
        }
    }

    public TrainSQL(String url, String root, String password) {
        connected = dbs.establishConnection(url, root, password);
        System.out.println(connected ? "Connection Made!" : "Connection Failed!");
    }
}
import java.util.Random;

public class Bot {
    private static final Random RANDOM = new Random();
    private static final int [] valueRandom = new int[2];
    private static String sideSymbol;
    public static void makeTurn() {
        do {
            valueRandom[0] = RANDOM.nextInt(0,3);
            valueRandom[1] = RANDOM.nextInt(0,3);
        } while (Board.cellIsBusy(valueRandom));
        Board.setValue(valueRandom, sideSymbol);
    }
    public static void setSideSymbol(String string) {
        sideSymbol = string;
    }

}

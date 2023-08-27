import java.util.Random;

public class Bot {
    private static final int PLAYER = 0;
    private static final Random RANDOM = new Random();
    private static final int [] valueRandom = new int[2];
    private static String sideSymbol;

    public static void test() {
        for (int i = 0; i < 100; i++) {
            System.out.print(RANDOM.nextInt(0,3));
        }
    }
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
    public static String getSideSymbol() {
        return sideSymbol;
    }
}

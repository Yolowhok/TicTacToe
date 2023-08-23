import java.util.Arrays;


/**
 * X = 1
 * O = -1
 * NULL = 0
 *
 * **/
public class Board {
    private static final int COLUMN_SIZE = 3;
    private static final int ROW_SIZE = 3;
    private static final int VALUE_DECREASING = 1;
    private static  final String CROSS_SYMBOL = "X";
    private static final String ZERO_SYMBOL = "O";
    private static final String EMPTY_SYMBOL = " ";
    private static String[][] board;

    public static void test() throws InterruptedException {
        initialize();
        renderBoard();
        setValue("1 2", CROSS_SYMBOL);
        renderBoard();
    }
    public static void initialize() {
        board = new String[COLUMN_SIZE][ROW_SIZE];
        for (int column = 0; column < COLUMN_SIZE; column++) {
            for (int row = 0; row < ROW_SIZE; row++) {
                board[column][row] = ZERO_SYMBOL;
            }
        }
    }

    public static boolean checkBoardState() {
        return true;
    }

    public static void renderBoard() throws InterruptedException {
        System.out.println();
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                System.out.print(board[row][column]);
            }
            Thread.sleep(200);
            System.out.println();
        }
    }
    public static void setValue(String value, String sideSymbol) {
        int[] finalValue = stringToIntArray(value);
        switch (sideSymbol){
            case CROSS_SYMBOL -> board[finalValue[0]][finalValue[1]] = CROSS_SYMBOL;
            case ZERO_SYMBOL -> board[finalValue[0]][finalValue[1]] = ZERO_SYMBOL;

        }



    }
    private static int[] stringToIntArray(String value) {
        int [] values = Arrays.stream(value.split(" "))
                .mapToInt(Integer::parseInt).toArray();
        values[0]-=1;
        values[1]-=1;
        return values;
    }



}

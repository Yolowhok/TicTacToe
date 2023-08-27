import java.util.ArrayList;
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
    private static final String CROSS_SYMBOL = "X⃣"; //"X";
    private static final String ZERO_SYMBOL = "O⃣"; //"O";
    private static final String STRIKE_THROUGH_CROSS_SYMBOL = " X̶⃣";
    private static final String STRIKE_THROUGH_ZERO_SYMBOL = " O̶⃣";

    private static final String EMPTY_SYMBOL = " ";
    private static boolean roundIsOver = false;
    private static String[][] board;

    public static void test() throws InterruptedException {
        initialize();
        renderBoard();
        setValue(new int[]{1, 0}, CROSS_SYMBOL);
        setValue(new int[]{2, 1}, CROSS_SYMBOL);
        setValue(new int[]{2, 2}, CROSS_SYMBOL);
        renderBoard();
        checkBoardState();
    }
    public static void initialize() {
        roundIsOver = false;
        board = new String[COLUMN_SIZE][ROW_SIZE];
        for (int column = 0; column < COLUMN_SIZE; column++) {
            for (int row = 0; row < ROW_SIZE; row++) {
                board[column][row] = EMPTY_SYMBOL;
            }
        }
    }
    public static void text1() {
        System.out.println(STRIKE_THROUGH_CROSS_SYMBOL + STRIKE_THROUGH_CROSS_SYMBOL + STRIKE_THROUGH_CROSS_SYMBOL);
    }
    public static void checkBoardState() throws InterruptedException {
        ArrayList<Integer> sum = new ArrayList<>();
        int [][] matrix = boardConverter();

        checkRowFieldsSum(matrix, sum);
        checkColumnsFieldsSum(matrix, sum);
        checkLeftDiagonalSum(matrix, sum);
        checkRightDiagonalSum(matrix, sum);

        if (sum.contains(3)) {
            System.out.println("Крестики победили\n");
            roundIsOver = true;
            renderBoard(STRIKE_THROUGH_CROSS_SYMBOL, sum.indexOf(3));
            System.out.println(sum.indexOf(3));
        } else if (sum.contains(-3)) {
            System.out.println("Нолики победили\n");
            renderBoard(STRIKE_THROUGH_ZERO_SYMBOL, sum.indexOf(-3));
            roundIsOver = true;
        } else if (boardFieldsIsOver()){
            System.out.println("Ничья\n");
            roundIsOver = true;
        }

    }
    public static boolean boardFieldsIsOver() {
        int counter = 0;
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                if (board[row][column].equals(EMPTY_SYMBOL)) {
                    counter++;
                }
            }
        }
        return counter == 0;
    }
    public static boolean isRoundIsOver() {
        return roundIsOver;
    }
    public static void renderBoard() throws InterruptedException {
        System.out.println();
        for (int row = 0; row < ROW_SIZE; row++) {
            System.out.print("|");
            for (int column = 0; column < COLUMN_SIZE; column++) {
                System.out.print(board[row][column] + "|");
            }
            Thread.sleep(200);
            System.out.println();
        }
    }
    public static void renderBoard(String strikeThroughSymbol, int index) {
        switch (index) {
            case 0 -> lineEdit("Horizontal", 0, strikeThroughSymbol);
            case 1 -> lineEdit("Horizontal", 1, strikeThroughSymbol);
            case 2 -> lineEdit("Horizontal", 2, strikeThroughSymbol);
            case 3 -> lineEdit("Vertical", 0, strikeThroughSymbol);
            case 4 -> lineEdit("Vertical", 1, strikeThroughSymbol);
            case 5 -> lineEdit("Vertical", 2, strikeThroughSymbol);
            case 6 -> lineEdit("LDiagonal", 6, strikeThroughSymbol);
            case 7 -> lineEdit("RDiagonal", 7, strikeThroughSymbol);
        }
    }

    public static void setValue(int [] value, String sideSymbol) {
            board[value[0]][value[1]] = sideSymbol;
    }
    public static boolean cellIsBusy(int [] strings) {
        return !board[strings[0]][strings[1]].equals(EMPTY_SYMBOL);
    }
    private static int[][] boardConverter() {
        int [][] ints = new int[ROW_SIZE][COLUMN_SIZE];
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                ints[row][column] = switch (board[row][column]) {
                    case CROSS_SYMBOL -> 1;
                    case ZERO_SYMBOL -> -1;
                    case EMPTY_SYMBOL -> 0;
                    default -> throw new IllegalStateException("Unexpected value: " + board[row][column]);
                };
            }
        }
        return ints;
    }
    public static String getCrossSymbol() {
        return CROSS_SYMBOL;
    }
    public static String getZeroSymbol() {
        return ZERO_SYMBOL;
    }
    public static String getEmptySymbol() {
        return EMPTY_SYMBOL;
    }
    private static void checkRowFieldsSum(int [][] matrix, ArrayList<Integer> sum) {
        for (int row = 0; row < ROW_SIZE; row++) {
            int rowSum = 0;
            for (int column = 0; column < COLUMN_SIZE; column++) {
                rowSum += matrix[row][column];
            }
            sum.add(rowSum);
        }
    }
    private static void checkColumnsFieldsSum(int [][] matrix, ArrayList<Integer> sum) {
        for (int columns = 0; columns < COLUMN_SIZE; columns++) {
            int columnsSum = 0;
            for (int row = 0; row < ROW_SIZE; row++) {
                columnsSum+= matrix[row][columns];
            }
            sum.add(columnsSum);
        }
    }
    private static void checkLeftDiagonalSum(int [][] matrix, ArrayList<Integer> sum) {
        int leftDiagonalSum = 0;
        for (int row = 0; row < ROW_SIZE; row++) {
            leftDiagonalSum+=matrix[row][row];
        }
        sum.add(leftDiagonalSum);
    }
    private static void checkRightDiagonalSum(int [][] matrix, ArrayList<Integer> sum) {
        int rightDiagonalSum = 0;
        for (int row = 0; row < ROW_SIZE; row++) {
            rightDiagonalSum+= matrix[row][ROW_SIZE-row-1];
        }
        sum.add(rightDiagonalSum);
    }
    private static void lineEdit(String line, int index, String strikeThroughSymbol) {
        switch (line) {
            case "Horizontal":
                for (int i = 0; i < ROW_SIZE; i++) {
                    board[index][i] = strikeThroughSymbol;
                }
            case "Vertical":
                for (int i = 0; i < COLUMN_SIZE; i++) {
                    board[i][index] = strikeThroughSymbol;
                }
            case "LDiagonal":
                for (int i = 0; i < ROW_SIZE; i++) {
                    board[i][i] = strikeThroughSymbol;
                }
            case "RDiagonal":
                for (int i = 0; i < ROW_SIZE; i++) {
                    board[i][ROW_SIZE-i-1] = strikeThroughSymbol;
                }
            }
        }
    }


import java.util.Random;

public class BotMinimaxAI {
    private static final Random RANDOM = new Random();
    private static String sideSymbol;
    private static String opponentSymbol;
    private static final String CROSS_SYMBOL = "X⃣"; //"X";
    private static final String ZERO_SYMBOL = "O⃣"; //"O";

    // Основной метод для выбора хода
    public static void makeTurn() {
        int[] bestMove = findBestMove();
        if (bestMove != null) {
            Board.setValue(bestMove, sideSymbol);
        } else {
            // Если не осталось ходов, генерируем случайный ход
            int[] valueRandom = generateRandomMove();
            Board.setValue(valueRandom, sideSymbol);
        }
    }

    // Метод для генерации случайного хода
    private static int[] generateRandomMove() {
        int[] valueRandom = new int[2];
        do {
            valueRandom[0] = RANDOM.nextInt(3);
            valueRandom[1] = RANDOM.nextInt(3);
        } while (Board.cellIsBusy(valueRandom));
        return valueRandom;
    }

    // Метод для нахождения лучшего хода
    private static int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        // Проходим по всем клеткам доски
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!Board.cellIsBusy(new int[]{i, j})) {
                    Board.setValue(new int[]{i, j}, sideSymbol);
                    int score = minimax(Board.getBoard(), 0, false); // Используем Minimax
                    Board.clearCell(new int[]{i, j}); // Очищаем клетку

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    // Реализация алгоритма Minimax
    private static int minimax(String[][] board, int depth, boolean isMaximizing) {
        if (Board.checkWin(sideSymbol)) return 10 - depth; // Если бот выиграл
        if (Board.checkWin(opponentSymbol)) return depth - 10; // Если противник выиграл
        if (Board.isFull()) return 0; // Ничья

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!Board.cellIsBusy(new int[]{i, j})) {
                        Board.setValue(new int[]{i, j}, sideSymbol);
                        int score = minimax(board, depth + 1, false);
                        Board.clearCell(new int[]{i, j});
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!Board.cellIsBusy(new int[]{i, j})) {
                        Board.setValue(new int[]{i, j}, opponentSymbol);
                        int score = minimax(board, depth + 1, true);
                        Board.clearCell(new int[]{i, j});
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public static void setSideSymbol(String string) {
        sideSymbol = string;
        opponentSymbol = sideSymbol.equals(CROSS_SYMBOL) ? ZERO_SYMBOL : CROSS_SYMBOL; // Определяем символ противника
    }
}
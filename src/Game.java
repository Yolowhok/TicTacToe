import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

    public static int rightOfFirstMove;
    public static Scanner scanner = new Scanner(System.in);
    public static StringBuilder stringBuilder;
    private static final Pattern pattern = Pattern.compile("^[1-2]{1}");



    public static void newGame() throws InterruptedException {
        choiseXorO();
        choiseFirstPlayer();
        startRound();
     }
    private static void gameLoop() throws InterruptedException {
        do {
            startRound();
        } while (Game.gameIsOver());
        startNextRound();
    }
    public static void test() throws InterruptedException {
        newGame();
        for (int i = 0; i < 3; i++) {
            Player.makeTurn();
            Board.renderBoard();
            Bot.makeTurn();
            Board.renderBoard();
        }
    }
    public static void startRound() throws InterruptedException {
        Board.initialize();
        switch (rightOfFirstMove) {
            case 1: startRoundPlayerThenBot();
            break;
            case 2: startRoundBotThenPlayer();
            break;
        }
        startNextRound();
    }
    public static void startNextRound() throws InterruptedException {
        System.out.println("Начать следующий раунд?\n" +
                "1. Да\n" +
                "2. Нет\n");
        if (askNextRound()) {
            startRound();
        }
    }
    public static void startRoundPlayerThenBot() throws InterruptedException {
        do {
            Board.renderBoard();

            Player.makeTurn();
//            Board.checkBoardState();


            Bot.makeTurn();
            Board.checkBoardState();
        }
        while (!gameIsOver());
        Board.renderBoard();
    }
    public static void startRoundBotThenPlayer() throws InterruptedException {
        do {
            Board.renderBoard();
            Bot.makeTurn();
            Board.checkBoardState();
            Board.renderBoard();
            Player.makeTurn();
            Board.checkBoardState();}
        while (!gameIsOver());

    }
    private static void choiseFirstPlayer() {
        System.out.println("Чьё право на первый ход? \n 1. Player \n 2. Bot \n\n Введите номер игрока:");
        while (true) {
            StringBuffer stringBuffer = new StringBuffer(scanner.next());
            if (pattern.matcher(stringBuffer.toString()).matches()) {
                rightOfFirstMove = Integer.parseInt(stringBuffer.toString());
                System.out.println("\nВыбран игрок под номером " + rightOfFirstMove);
                break;
            } else {
                System.out.println("Введите номер игрока");
            }
        }
    }
    private static void choiseXorO() {
        while (true) {
            System.out.println(" " + "X⃣" + " или " + "O⃣" + " ?");
            stringBuilder = new StringBuilder(scanner.next());
            if (Objects.equals(stringBuilder.toString(), String.valueOf(1))) {
                Player.setSideSymbol(Board.getCrossSymbol());
                Bot.setSideSymbol(Board.getZeroSymbol());
                break;
            } else if (Objects.equals(stringBuilder.toString(), String.valueOf(2))) {
                Player.setSideSymbol(Board.getZeroSymbol());
                Bot.setSideSymbol(Board.getCrossSymbol());
                break;
            } else {
                System.out.println("Введите 1 или 2");
            }
        }
    }
    private static boolean gameIsOver() {
        return Board.boardFieldsIsOver() || Board.isRoundIsOver();
    }
    private static boolean askNextRound() {
        while (true) {
            String str = scanner.next();
            if (pattern.matcher(str).matches()) {
                switch (str) {
                    case "1" -> {
                        return true;
                    }
                    case "2" -> {
                        return false;
                    }
                }
            } else {
                System.out.println("Введите номер вариант");
            }
        }
    }
    public static void getStat() {
        System.out.println(Player.getSideSymbol());
        System.out.println(Bot.getSideSymbol());
        System.out.println(rightOfFirstMove);
    }
}

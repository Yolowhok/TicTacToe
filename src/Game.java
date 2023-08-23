import java.util.Objects;
import java.util.Scanner;

public class Game {

    public static int rightOfFirstMove;
    public static Scanner scanner = new Scanner(System.in);
    public static StringBuilder stringBuilder;


    public static void newGame() {
        choiseXorO();
        choiseFirstPlayer();
     }
    private static void gameLoop() {
        while (Board.checkBoardState()) {
            startRound();
        }
    }
    public static void startRound() {
        switch (rightOfFirstMove) {
            case 1: startRoundPlayerThenBot();
            case 2: startRoundBotThenPlayer();

        }
    }
    public static void startRoundPlayerThenBot() {

    }
    public static void startRoundBotThenPlayer() {

    }





    private static void choiseFirstPlayer() {
        while (true) {
            System.out.println("Чьё право на первый ход? \n 1. Player \n 2. Bot \n\n Введите номер игрока:");
            rightOfFirstMove = Integer.parseInt(scanner.next());
            if (Objects.equals(rightOfFirstMove, 1) || Objects.equals(rightOfFirstMove, 2)) {
                System.out.println("\nВыбран игрок под номером " + rightOfFirstMove);
                break;
            } else {
                System.out.println("Введите номер игрока");
            }
        }
    }
    private static void choiseXorO() {
        while (true) {
            System.out.println("Х или О?");
            stringBuilder = new StringBuilder(scanner.next());
            if (Objects.equals(stringBuilder.toString(), String.valueOf(1))) {
                Player.sideSymbol = "X";
                Bot.sideSymbol = "O";
                break;
            } else if (Objects.equals(stringBuilder.toString(), String.valueOf(2))) {
                Player.sideSymbol = "O";
                Bot.sideSymbol = "X";
                break;
            } else {
                System.out.println("Введите 1 или 2");
            }
        }
    }
}

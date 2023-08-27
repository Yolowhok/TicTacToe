import java.util.Arrays;
import java.util.regex.Pattern;

public class Player {
    private static final int PLAYER = 1;
    private static final Pattern pattern = Pattern.compile("^[1-3]{1}(\\s)[1-3]{1}");
    private static String sideSymbol;
    public static void makeTurn() {
        int [] cellValue;
        while (true) {
            StringBuilder stringBuilder = new StringBuilder(Game.scanner.nextLine());
            if (pattern.matcher(stringBuilder.toString()).matches()) {
                cellValue = stringToIntArray(stringBuilder.toString());
                if (!Board.cellIsBusy(cellValue)) {
                    Board.setValue(cellValue, sideSymbol);
                    break;
                } else {
                    System.out.println("Поле занято!");
                }
            } else {
                System.out.println("Введите две координаты от 1 до 3 через пробел (Пример: \"1 1\")");
            }
        }

    }

    public static void setSideSymbol(String string) {
        sideSymbol = string;
    }
    public static String getSideSymbol() {
        return sideSymbol;
    }
    private static int[] stringToIntArray(String value) {
        int [] values = Arrays.stream(value.split(" "))
                .mapToInt(Integer::parseInt).toArray();
        values[0]-=1;
        values[1]-=1;
        return values;
    }
}

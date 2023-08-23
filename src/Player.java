import java.util.regex.Pattern;

public class Player {
    public static String sideSymbol;
    private static final Pattern pattern = Pattern.compile("^[1-3]{1}(\\s)[1-3]{1}");
    public static void makeTurn() {
        while (true) {
            StringBuilder stringBuilder = new StringBuilder(Game.scanner.nextLine());
            if (pattern.matcher(stringBuilder.toString()).matches()) {
                Board.setValue(stringBuilder.toString(), sideSymbol);
            } else {
                System.out.println("Введите две координаты от 1 до 3 через пробел (Пример: \"1 1\"");
            }
        }

    }
    private void validateData(StringBuilder stringBuilder) {

    }
    public String getSideSymbol() {
        return sideSymbol;
    }
}

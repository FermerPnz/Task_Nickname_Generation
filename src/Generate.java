import java.util.Random;

public class Generate {
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();


    }

    public static boolean FlipOver(String text) {
        return text.contentEquals(new StringBuilder(text).reverse());
    }

    public static boolean Identical(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != text.charAt(i - 1))
                return false;
        }
        return true;
    }

    public static boolean Ascending_Order(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) > text.charAt(i - 1))
                return false;
        }
        return true;
    }

    public static void incrementCounter(int text) {
        switch (text) {
            case 3 -> Main.Counter_Var_3.incrementAndGet();
            case 4 -> Main.Counter_Var_4.incrementAndGet();
            case 5 -> Main.Counter_Var_5.incrementAndGet();
        }
    }
}

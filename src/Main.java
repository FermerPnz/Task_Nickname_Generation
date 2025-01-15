import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger counter_Var_3 = new AtomicInteger();
    public static AtomicInteger counter_Var_4 = new AtomicInteger();
    public static AtomicInteger counter_Var_5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Generate.generateText("abc", 3 + random.nextInt(3));
        }

        Thread flipOver = new Thread(() ->
        {
            for (String text : texts) {
                if (Generate.FlipOver(text) && !Generate.Identical(text) && !Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());

                }
            }
        });
        flipOver.start();

        Thread identical = new Thread(() -> {
            for (String text : texts) {
                if (!Generate.FlipOver(text) && Generate.Identical(text) && !Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());
                }
            }
        });
        identical.start();

        Thread ascending_Order = new Thread(() -> {
            for (String text : texts) {
                if (!Generate.FlipOver(text) && !Generate.Identical(text) && Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());
                }
            }
        });
        ascending_Order.start();

        identical.join();
        ascending_Order.join();
        flipOver.join();

        System.out.println("Слов с длиной 3: " + counter_Var_3 + " шт");
        System.out.println("Слов с длиной 4: " + counter_Var_4 + " шт");
        System.out.println("Слов с длиной 5: " + counter_Var_5 + " шт");
    }
}
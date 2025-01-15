import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger Counter_Var_3 = new AtomicInteger();
    public static AtomicInteger Counter_Var_4 = new AtomicInteger();
    public static AtomicInteger Counter_Var_5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Generate.generateText("abc", 3 + random.nextInt(3));
        }

        Thread FlipOver = new Thread(() ->
        {
            for (String text : texts) {
                if (Generate.FlipOver(text) && !Generate.Identical(text) && !Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());

                }
            }
        });
        FlipOver.start();

        Thread Identical = new Thread(() -> {
            for (String text : texts) {
                if (!Generate.FlipOver(text) && Generate.Identical(text) && !Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());
                }
            }
        });
        Identical.start();

        Thread Ascending_Order = new Thread(() -> {
            for (String text : texts) {
                if (!Generate.FlipOver(text) && !Generate.Identical(text) && Generate.Ascending_Order(text)) {
                    Generate.incrementCounter(text.length());
                }
            }
        });
        Ascending_Order.start();

        Identical.join();
        Ascending_Order.join();
        FlipOver.join();

        System.out.println("Слов с длиной 3: " + Counter_Var_3 + " шт");
        System.out.println("Слов с длиной 4: " + Counter_Var_4 + " шт");
        System.out.println("Слов с длиной 5: " + Counter_Var_5 + " шт");
    }
}
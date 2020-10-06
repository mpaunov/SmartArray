import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SmartArray smartArray = new SmartArray();

        for (int i = 0; i < 1024; i++) {
            smartArray.add(i + 1);
        }

        boolean isTrue = true;

        for (int i = 0; i < 1023; i++) {
            smartArray.remove(1023 - i);
        }

        smartArray.forEach(System.out::println);
    }
}

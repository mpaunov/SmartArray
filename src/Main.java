import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SmartArray smartArray = new SmartArray();

        Stack stack = new Stack();

        for (int i = 0; i < 10; i++) {
            stack.push(i + 1);
        }

        stack.forEach(System.out::println);
    }
}

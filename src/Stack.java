import java.util.function.Consumer;

public class Stack {
    private static class Node {
        private int element;
        private Node prev;

        private Node(int element) {
            this.element = element;
        }
    }

    private Node top;
    private int size;

    public Stack() {
    }

    public void push(int element) {
        Node newNode = new Node(element);
        if (this.top != null) {
            newNode.prev = this.top;
        }
        this.top = newNode;
        this.size++;
    }

    public int pop() {
        ensureNonEmpty();

        int result = this.top.element;
        this.top = this.top.prev;
        this.size--;

        return result;
    }

    private void ensureNonEmpty() {
        if (this.top == null) {
            throw new IllegalStateException("Empty Stack");
        }
    }

    public int peek() {
        ensureNonEmpty();
        return this.top.element;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void forEach(Consumer<Integer> consumer) {
        Node current = this.top;

        while (current != null) {
            consumer.accept(current.element);
            current = current.prev;
        }
    }
}

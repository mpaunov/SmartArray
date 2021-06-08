import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SmartArray {

    private static final int INITIAL_CAPACITY = 4;

    private int[] elements;
    private int size;

    public SmartArray() {
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(int element) {
        if (size == elements.length) {
            elements = grow();
        }
        this.elements[size++] = element;
    }

    private int[] grow() {
        int[] newElements = new int[elements.length * 2];

        System.arraycopy(elements, 0,
                newElements, 0, elements.length);

        return newElements;
    }

    public int get(int index) {
        ensureIndex(index);
        return this.elements[index];
    }

    public int size() {
        return this.size;
    }

    public int remove(int index) {
        int element = get(index);

        if (this.size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements, index, this.size - 1 - index);
        }

        elements[--this.size] = 0;

        if (this.size == 0) {
            this.elements = new int[INITIAL_CAPACITY];
        }

        if (this.size < elements.length / 4 && elements.length > INITIAL_CAPACITY) {
            this.elements = shrink();
        }

        return element;
    }

    private int[] shrink() {
        int[] newElements = new int[elements.length / 2];

        System.arraycopy(elements, 0, newElements, 0, this.size);

        return newElements;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public boolean contains(int element) {
        return IntStream.range(0, this.size)
                .anyMatch(i -> elements[i] == element);
    }

    public void add(int index, int element) {
        ensureIndex(index);
        int lastElement = elements[this.size - 1];

        if (this.size - 1 - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, this.size - 1 - index);
        }

        elements[index] = element;

        add(lastElement);
    }

    public void forEach(Consumer<Integer> consumer) {
        IntStream.range(0, this.size)
                .forEach(i -> consumer.accept(elements[i]));
    }
}

import java.util.function.Consumer;

public class SmartArray {
    private static final int INITIAL_CAPACITY = 4;

    private int[] elements;
    private int size;

    public SmartArray() {
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(int element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        this.elements[this.size] = element;
        this.size++;
    }

    public int get(int index) {
        validateIndex(index);
        return this.elements[index];
    }

    public boolean contains(int element) {
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i] == element) {
                return true;
            }
        }

        return false;
    }

    public void add(int index, int element) {
        validateIndex(index);

        int lastElement = this.elements[this.size - 1];

        for (int i = this.size - 1; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = element;

        this.add(lastElement);
    }

    public int remove(int index) {
        validateIndex(index);
        int element = this.elements[index];

        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[this.size - 1] = 0;

        this.size--;

        if (this.elements.length > 4 && this.size <= this.elements.length / 4) {
            this.elements = shrink();
        }

        return element;
    }

    private int[] shrink() {
        int[] newElements = new int[this.elements.length / 2];

        System.arraycopy(this.elements, 0, newElements, 0, newElements.length);

        return newElements;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds " +
                    "for size " + this.size);
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.elements[i]);
        }
    }

    private int[] grow() {
        int[] newElements = new int[this.size * 2];

        System.arraycopy(this.elements, 0, newElements, 0, this.elements.length);

        return newElements;
    }
}

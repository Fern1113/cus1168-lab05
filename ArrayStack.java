package homework4;

public class ArrayStack<T extends Number> implements CustomStack<T> {
    // Constants for stack configuration
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    // Static variables for tracking across all instances
    private static int totalStacks = 0;
    private static int totalElements = 0;

    // Instance variables
    private Object[] elements;
    private int top;
    private int operationCount;
    private final int stackId;

    public ArrayStack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.top = -1;
        this.operationCount = 0;
        this.stackId = ++totalStacks;
    }

    @Override
    public void push(T element) {
        operationCount++;
        if (top == elements.length - 1) {
            resize();
        }
        elements[++top] = element;
        totalElements++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        operationCount++;
        if (isEmpty()) {
            return null;
        }
        T element = (T) elements[top];
        elements[top--] = null;
        totalElements--;
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        operationCount++;
        if (isEmpty()) {
            return null;
        }
        return (T) elements[top];
    }

    @Override
    public boolean isEmpty() {
        operationCount++;
        return top == -1;
    }

    @Override
    public int size() {
        operationCount++;
        return top + 1;
    }

    private void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public void addTopTwo() {
        if (size() < 2) {
            System.out.println("Not enough elements to add.");
            return;
        }
        T num1 = pop();
        T num2 = pop();
        
        if (num1 instanceof Integer && num2 instanceof Integer) {
            push((T) Integer.valueOf(num1.intValue() + num2.intValue()));
        } else {
            push((T) Double.valueOf(num1.doubleValue() + num2.doubleValue()));
        }
    }

    public String getStats() {
        return "Stack #" + stackId + ": Size=" + size() + ", Operations=" + operationCount;
    }

    public static String getGlobalStats() {
        return "Total stacks: " + totalStacks + ", Total elements: " + totalElements;
    }
}

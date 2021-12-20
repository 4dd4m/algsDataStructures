package arch;

public class Stack<T> implements StackInterface{
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    protected int[] data;
    protected int size;

    public Stack() {
        firstNode = null;
        size = 0;
    }

    public int[] peek() {
        return (int[]) firstNode.getData();
    }

    public int getSize() { return size; }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(int[] newEntry) {
        Node<T> newNode = new Node(newEntry);
        newNode.setNext(firstNode);
        int[] arr = newEntry;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int tmp = 0;
                if (arr[i] < arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
            newNode.setData((T) arr);
            firstNode = newNode;
            size++;
            if (size == 1) {
                lastNode = newNode;
            }
            return true;
        }

        public int[] pop () {
            if (firstNode != null) {
                int[] result = (int[]) firstNode.getData();
                firstNode = firstNode.getNext();
                size--;
                return result;
            } else return null;
        }

        public void clear () {
            if (firstNode != null) {
                firstNode.setNext(null);
                size = 0;
            }
        }

        public String toString () {
            if (isEmpty()) {
                return "[]";
            }
            String result = "[";
            Node<T> currentNode = firstNode;

            while (currentNode.getNext() != null) {
                for (int i : (int[]) currentNode.getData()) {
                    result += i;
                }
                result += "|";
                currentNode = currentNode.getNext();

            }
            for (int i : (int[]) lastNode.getData()) {
                result += i;
            }
            result += "]";
            return result;
        }
    }


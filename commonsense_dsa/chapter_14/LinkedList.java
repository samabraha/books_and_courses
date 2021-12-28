import java.util.Objects;

class Node {
    String data;
    Node next = null;

    public Node(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Node)) {
            return false;
        }

        return ((Node) obj).data.equalsIgnoreCase(this.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.data);
    }
}

public class LinkedList {
    Node first;
    int size = 0;

    public LinkedList(String data) {
        this.first = new Node(data);
        size++;
    }

    public void add(String data) {
        Node node = new Node(data);
        add(node);
    }

    public void add(Node node) {
        if (size == 0) {
            first = node;

        } else {
            var currentNode = first;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = node;
        }
        size++;
    }

    public void addAtIndex(int index, String data) throws IllegalAccessException {
        addAtIndex(index, new Node(data));
    }

    public void addAtIndex(int index, Node node) throws IllegalAccessException {
        if (index > size) {
            throw new IllegalAccessException("index:$1%d greater than size:$2%d".formatted(index, size));
        }

        var currentNode = first;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next;
        }

        var newNode = node;
        newNode.next = currentNode.next;
        currentNode.next = newNode;
    }

    public boolean delete(String data) {
        return delete(new Node(data));
    }

    public boolean delete(Node deleteNode) {
        if (size == 0) {
            return false;
        }

        var currentNode = first;
        if (currentNode.equals(deleteNode)){
            first = first.next;
            size--;
            return true;
        }
        while (currentNode.next != null) {
            if (currentNode.next.equals(deleteNode)) {
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    private void printAll() {
        if (size == 0) {
            System.out.println("Nothing to print.");
            return;
        }

        var currentNode = first;
        System.out.print(currentNode + " ");
        while (currentNode.next != null) {
            System.out.print(currentNode.next + " ");
            currentNode = currentNode.next;
        }
    }

    private void reverse() {
        if (size == 0) { return; }

        var currentItem = first;
        while (currentItem.next != null) {
            var nxt = currentItem.next;
            nxt.next = currentItem;
            currentItem = nxt;
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        LinkedList linkedList = new LinkedList("Socrates");
        linkedList.add("Plato");
        linkedList.add("12");
        linkedList.addAtIndex(linkedList.size, "Xenophon");
        linkedList.printAll();
        System.out.println();
        linkedList.delete("Socrates");

        linkedList.reverse();
        linkedList.printAll();
    }
}



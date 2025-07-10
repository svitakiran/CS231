import java.util.Iterator;    // defines the Iterator interface

public class LinkedList<T> {
    
    private static class Node<T> {
        private Node next;
        private T item;

        public Node(T item) {
            this.item = item;
        }

        public T getData() {
            return item;
        }

        public void setNext(Node<T> n) {
            this.next = n;
        }

        public Node getNext() {
            return next;
        }
        
    }

    private Node <T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String text = "";
        Node<T> walker = head;
        for (int i = 0; i < size; i++) {
            text += String.valueOf(walker.getData());
            walker = walker.getNext();
        }
        return text;
    }

    public void add(T item) {
        Node<T> node = new Node<T>(item);
        node.setNext(head);
        head = node;
        size++;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (head.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public T get(int index) {
        Node<T> walker = head;
        for (int i = 0; i < index-1; i++) {
            walker = walker.getNext();
        }
        return walker.getData();
    }

}
import java.lang.reflect.InaccessibleObjectException;
import java.util.Iterator;

import org.w3c.dom.Node;    

public class LinkedList<T> implements Queue<T>, Iterable<T>{

    private static class Node <T>{
        private Node <T> next;
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

        public Node <T> getNext() {
            return next;

        }
        
    }

    private Node <T> head;
    private Node <T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null; 
        size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        String text = "";
        Node <T> walker = head;
        for (int i = 0; i < size; i++) {
            System.out.println("In loop" + String.valueOf(walker.getData()));
            text += String.valueOf(walker.getData());
            walker = walker.getNext();
        }

        return text;
    }

    public void add(T item) {
        Node <T> newNode = new Node<T>(item);
        newNode.setNext(head);
        head = newNode;
        size++;

        if (size == 1) {
            head = newNode;
            tail = newNode;
        }
    }

    public T remove() {
        T temp = head.getData();
        if (size == 0) {
            throw new InaccessibleObjectException("list is empty");
        } else {
            head = head.getNext();
        }

        if (size == 1) {
            tail = null;
        }

        size--;
        return temp;

    }

    public void add(int index, T item) {
        if (index == 0) {
            Node<T> newNode = new Node<>(item);
            newNode.setNext(head);
            head = newNode;
            size++;
            if (size == 1) {
                tail = newNode;
            }
        } else if (index == size) { 
            addLast(item);
        } else if (index > 0 && index < size) {
            Node<T> walker = head;
            for (int i = 0; i < index - 1; i++) {
                walker = walker.getNext();
            }
            Node<T> newNode = new Node<>(item);
            newNode.setNext(walker.getNext());
            walker.setNext(newNode);
            size++;
        } else {
            throw new IndexOutOfBoundsException("");
        }
    }


    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    
        if (index == size - 1) {
            return removeLast();
        } else if (index == 0) {
            return remove();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            T removedData = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            size--;
            return removedData;
        }
        
        
        //return null;

        
    }

    public boolean contains(Object o) {
        Iterator<T> iterator = iterator();

        if(head == o) {
            return true;
        }

        while (iterator.hasNext()) {
            T next = iterator.next();
            if (next.equals(o)) {
                return true;
            }
        }
        return false;
    
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)){
            return false;
        }

        @SuppressWarnings("unchecked")
        LinkedList<T> oAsALinkedList = (LinkedList<T>) o;

        if(this.size() != oAsALinkedList.size()) {
            return false;
        }

        for(int i = 0; i<oAsALinkedList.size(); i++) {
            if(!(oAsALinkedList.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }

    public T get(int index) {
        Node<T> walker = head;
        for(int i = 0; i < index; i++) {
            walker = walker.getNext();
        }

        if (index == (size)) {
            getLast();
        }
        
        return walker.getData();
    }

    private class LLIterator implements Iterator<T> {
        private Node <T> currentNode;
        public LLIterator(Node <T> head) {
            currentNode = head;
        }

        public boolean hasNext() {
            if(currentNode != null) {
                return true;
            }

            else {
                return false; 
            }

        }

        public T next() {
            if (!hasNext()) { 
                System.out.println("list is empty or does not exist");
            }
            T data = currentNode.getData();
            currentNode = currentNode.getNext();
            return data;

        }

    }

    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    
    }

    public void addLast(T item) {
        Node<T> update = new Node<>(item);
        update.setNext(tail);
        tail = update;
        size++;
    }

    public T removeLast() {
        Node<T> last = head;
        while (last.next.next != null) {
            last = last.getNext();
        }
        tail = last;
        return last.getData();
    }

    public T getLast() {
        if(isEmpty()) {
            throw new NullPointerException("list is empty");
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    public void offer(T item) {
        addLast(item);
    }

    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    public T poll() {
        return remove();
    }

    
}

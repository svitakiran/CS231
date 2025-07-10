/*
 * The purpose of this file is to implement a LinkedList that implements
 * the Iterable interface.
 * Author: Svita Kiran
 * Date: 3/5/24
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

public class LinkedList<T> implements Iterable<T> {
    
    private static class Node<T> {
        
        private Node<T> next;
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

        public Node<T> getNext() {
            return next;
        }
    
    }

    private Node<T> head;
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
        return size == 0;
    }

    public void add(T item) {
        Node<T> newNode = new Node<> (item);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void add(int index, T item) {
        if (index == 0) {
            Node<T> newNode = new Node<>(item);
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> walker = head;
            for (int i = 0; i < index - 1; i++) {
                walker = walker.getNext();
            }
            Node<T> newNode = new Node<>(item);
            newNode.setNext(walker.getNext());
            walker.setNext(newNode);
        }
        size++;
    }

    public Iterator<T> iterator() {
        return new LLIterator(head);
    }

    private class LLIterator implements Iterator<T> {
    
        private Node<T> current;
        
        public LLIterator(Node<T> head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("no items left in list");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }

        public void remove() {
            throw new UnsupportedOperationException("nothing fix later");
        }
    }


}

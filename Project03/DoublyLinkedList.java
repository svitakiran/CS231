/*
 * The purpose of this file is to allow access to the next and previous
 * elements in the list. 
 * Author: Svita Kiran
 * Date: 3/7/24
*/

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    private static class Node<F> {
        F val;
        Node<F> prev, next;

        public Node(F v, Node<F> p, Node<F> n) {
            val = v;
            p = prev;
            next = n;
        }
    }

    Node<E> first, last;
    int size;

    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns the number of items in the list.
     * @return the number of items in the list.
     */
    public int size(){
        return size;
    }

    /**
     * Returns the first item in the list.
     * 
     * @return the first item in the list.
     */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return first.val;
    }

    /**
     * Returns the last item in the list.
     * 
     * @return the last item in the list.
     */
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return last.val;
    }

    /**
     * Adds the given item to the start of the list.
     * 
     * @param item the item to be added.
     */
    public void addFirst(E item) {
        Node<E> newNode = new Node<>(item, null, first);
        if (size == 0) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    /**
     * Adds the given item to the end of the list.
     * 
     * @param item the item to be added.
     */
    public void addLast(E item) {
        Node<E> newNode = new Node<>(item, last, null);
        if (size == 0) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    /**
     * Returns and removes the first item in the list.
     * 
     * @return the first item in the list.
     */
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        E val = first.val;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        size--;
        return val;
    }

    /**
     * Returns and removes the last item in the list.
     * 
     * @return the last item in the list.
     */
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        E val = last.val;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return val;
    }
}
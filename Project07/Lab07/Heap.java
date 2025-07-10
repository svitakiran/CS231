/*
 * The purpose of this file is to create a heap.
 * Author: Svita Kiran
 * Date: 4/23/24
*/

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> implements PriorityQueue<T> {
    private Comparator<T> comparator;
    private ArrayList<T> array;

    public Heap(Comparator<T> comparator1, boolean maxHeap) {
        if (maxHeap) {
            this.comparator = comparator1;
        } else {
            this.comparator = new Comparator<T>() {
                public int compare(T o1, T o2) {
                    return comparator1.compare(o1, o2);
                }
            };
        }
        this.array = new ArrayList<>();
    }

    public Heap(Comparator<T> comparator) {
        this(comparator, false);
        this.array = new ArrayList<>();
    }

    private void swap(int idx1, int idx2) {
        T temp = array.get(idx1);
        array.set(idx1, array.get(idx2));
        array.set(idx2, temp);

    }

    private int getParentIdx( int idx ) {
        return (idx - 1) / 2;
    }

    private int getLeftChildIdx( int idx ) {
        return (idx * 2) + 1;
    }

    private int getRightChildIdx( int idx ) {
        return (idx * 2) + 2;
    }

    private void bubbleUp(int idx) {
        if (idx == 0) {
            return;
        }
    
        int parentIndex = getParentIdx(idx);
    
        if (comparator.compare(array.get(idx), array.get(parentIndex)) < 0) {
            swap(idx, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    private void bubbleDown(int idx) {
        int leftChild = getLeftChildIdx(idx);
        int rightChild = getRightChildIdx(idx);
        
        if(array.size() <= rightChild && array.size() <= leftChild) {
            return;
        }

        if (array.size() <= leftChild) {
            if(array.size() <= rightChild) {
                if(comparator.compare(array.get(leftChild), array.get(rightChild)) < 0) {
                    if(comparator.compare(array.get(idx), array.get(leftChild)) < 0) {
                        return;
                    } else {
                        swap(idx, leftChild);
                    }
                }

                else if(comparator.compare(array.get(rightChild), array.get(leftChild)) < 0) {
                    if (comparator.compare(array.get(idx), array.get(rightChild)) < 0) {
                        return;
                    } else {
                        swap(idx, rightChild);
                    }
                }
            }
        } else {
            if(comparator.compare(array.get(idx), array.get(leftChild)) < 0) {
                return;
            } else {
                swap(idx, leftChild);
            }
        }

    }

    public String toString() {
        int depth = 0 ;
        return toString( 0 , depth );
    }
    
    private String toString( int idx , int depth ) {
        if (idx >= this.size() ) {
            return "";
        }
        String left = toString(getLeftChildIdx( idx ) , depth + 1 );
        String right = toString(getRightChildIdx( idx ) , depth + 1 );

        String myself = "\t".repeat(depth) + this.array.get( idx ) + "\n";
        return right + myself + left;
    }

    @Override
    public void offer(T item) {
        array.add(item);
        bubbleUp(array.size() - 1);
    }

    public int size() {
        return array.size();
    }

    public T peek() {
        if (array.size() == 0) {
            return null;
        }
        return array.get(0);
    }

    public T poll() {
        if (array.size() == 0) {
            return null;
        }
    
        T root = array.get(0);
        int lastSpot = array.size() - 1;
    
        swap(0, lastSpot);
        array.remove(lastSpot);
        bubbleDown(0);
    
        return root;
    }

    public void updatePriority(T item) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(item)) {
                int parentIdx = getParentIdx(i);
                if (i > 0 && comparator.compare(array.get(i), array.get(parentIdx)) < 0) {
                    bubbleUp(i);
                } 
                
                else {
                    bubbleDown(i);
                }

                return;
            }
        }
    }
}
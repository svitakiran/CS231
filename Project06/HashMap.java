/*
 * The purpose of this file is to build a Hash Map which maps a set of 
 * keys to specific values.
 * Author: Svita Kiran
 * Date: 4/16/24
*/

import java.util.*;
import org.w3c.dom.Node;

public class HashMap<K, V> implements MapSet<K, V> {
    private int size;
    private Node<K, V>[] nodes;
    private double maxLoadFactor;

    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> next;
        K key;
        V value;
    
        public Node(K key, V value) {
            super(key, value);
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashMap() {
        this(16, 0.75);
    }

    public HashMap(int capacity) {
        this(capacity, 0.75);
    }

    public HashMap(int capacity, double loadFactor) {
        this.nodes = new Node[capacity];
        this.maxLoadFactor = loadFactor;
        size = 0;
    }

    public int capacity() {
        return this.nodes.length;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity());
    }

    public String toString() {
        String output = "" ;
        for (int i = 0; i < this.capacity(); i++) {
           Node node = this.nodes[i];
           output += "bin " + i + ": ";
           while (node != null) {
               output += node.toString() + " | ";
               node = node.next;
           }
           output += "\n";
        }
       return output;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        size = 0;
        nodes = new Node[capacity()];
    }



    public V put(K key, V value) {
        int idx = hash(key);
        Node<K, V> cur = nodes[idx];
        Node<K, V> prev = null;

        while (cur !=  null) {
            if (cur.key.equals(key)) {
                V oldVal = cur.value;
                cur.value = value;
                return oldVal;
            }
            prev = cur;
            cur = cur.next;
        }

        Node<K, V> newNode = new Node<>(key, value);

        if (prev == null) {
            nodes[idx] = newNode;
        } else {
            prev.next = newNode;
        }
        size++;

        if ((double) size / capacity() > maxLoadFactor) {
            resize(capacity() * 2);
        }
        return null;
    }

    private void resize(int newCapacity) {
        Node<K, V>[] oldNodes = nodes;
        nodes = new Node[newCapacity];
        size = 0;

        for (Node<K,V> node : oldNodes) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    // public V get(K key) {
    //     int hashKey = hash(key) % size() % capacity();

    //     if (hashKey != 0) {
    //         return hashKey;
    //     } else {
    //         return null;
    //     }
    // }

    public boolean containsKey(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> node = nodes[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    nodes[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size --;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity(); i++) {
            Node<K, V> node = nodes[i];
            while (node != null) {
                keys.add(node.key);
                node = node.next;
            }
        }
        return keys;
    }

    @Override
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (int i = 0; i < capacity(); i++) {
            Node<K, V> node = nodes[i];
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
            
        }
        return values;
    }

    @Override
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity(); i++) {
            Node<K, V> node = nodes[i];
            while (node != null) {
                entries.add(new KeyValuePair<>(node.key, node.value));
                node = node.next;
            }
            
        }
        return entries;
    }

    // public String toString() {

    // }

    @Override
    public int maxDepth() {
        int maxDepth = 0;
        for (int i = 0; i < capacity(); i++) {
            Node<K, V> node = nodes[i];
            int depth = 0;
            while (node != null) {
                depth++;
                node = node.next;
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }
    
}

/*
 * The purpose of this file is to create a binary search tree map to use 
 * in WordCounter.
 * Author: Svita Kiran
 * Date: 3/18/24
*/

import java.util.*;
import java.util.Comparator;

import org.w3c.dom.Node;

public class BSTMap<K extends Comparable<K>, V> implements MapSet<K, V> {
    private Node<K, V> root;
    private int size;
    private Comparator<K> comparator;

    private static class Node<K, V> extends KeyValuePair<K, V> {
        public Node<K, V> left;
        public Node<K, V> right;
    
        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
        }
    }

    public BSTMap(Comparator<K> comparator) {
        if (comparator == null) {
            this.comparator = (k1, k2) -> k1.compareTo(k2);
        } else {
            this.comparator = comparator;
        }
        root = null;
        size = 0;
    }

    public BSTMap() {
        // find constructor
        this(null);
    }

    private String toString(Node cur, int curDepth) {
		if (cur == null){
			return "";
		} 
        //Ordering of these call matters for it to be sorted
        StringBuilder sb = new StringBuilder();
		String output = "\t".repeat(curDepth);
        sb.append(toString(cur.left, curDepth + 1));
        sb.append(output).append("<").append(cur.getKey()).append(" : ").append(cur.getValue()).append(">\n");
        sb.append(toString(cur.right, curDepth + 1));
        return sb.toString();
	}

	public String toString() {
		return toString(root, 0);
	}

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public V put(K key, V value) {
        if (value == null) {
            return null;
        }
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }
    }

    private V put(K key, V value, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) < 0){
            if (cur.left == null) {
                cur.left = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, cur.left);
            }
            
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right == null) {
                cur.right = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, cur.right);
            }
        } else {
            V value1 = cur.getValue();
            cur.setValue(value);
            return value1;
        }
    }
    
    public V get(K key, V value) {
        return get(key, root);
    }

    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> cur) {
        if (cur == null){
            return null;
        }
        if (comparator.compare(key, cur.getKey()) < 0){
            return get(key, cur.left);
            
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }

    public boolean containsKey(K key) {
        return get(key, root) != null;
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        keySet(root, keys);
        return keys;
    }

    private void keySet(Node<K, V> cur, ArrayList<K> output) {
        if (cur == null) {
            return;
        }
        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        values(root, values);
        return values;
    }

    private void values(Node<K, V> cur, ArrayList<V> output) {
        if (cur == null) {
            return;
        }
        values(cur.left, output);
        output.add(cur.getValue());
        values(cur.right, output);
    }

    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        entrySet(root, entries);
        return entries;
    }

    private void entrySet(Node<K, V> cur, ArrayList<KeyValuePair<K, V>> output) {
        if (cur == null) {
            return;
        }
        entrySet(cur.left, output);
        output.add(cur);
        entrySet(cur.right, output);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node<K, V> cur) {
        if (cur == null) {
            return 0;
        }
        
        int leftDepth = maxDepth(cur.left);
        int rightDepth = maxDepth(cur.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public V remove(K key) {
        Node<K, V>[] result = new Node[1];
        root = remove(root, key, result);
        if (result[0] != null) {
            size--;
            return result[0].getValue();
        }
        return null;
    }

    private Node<K, V> remove(Node<K, V> cur, K key, Node<K, V>[] result) {
        if (cur == null) {
            return null;
        }
        if (comparator.compare(key, cur.getKey()) < 0) {
            cur.left = remove(cur.left, key, result);
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            cur.right = remove(cur.right, key, result);
        } else {
            result[0] = cur;
            if (cur.left == null) {
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            } else {
                Node<K, V> next = findMin(cur.right);
                cur.right = remove(cur.right, next.getKey(), new Node[1]);
                next.left = cur.left;
                next.right = cur.right;
                return next;
            }
        }
        return cur;
    }

    private Node<K, V> findMin(Node<K, V> cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    public static void main(String[] args) {
        // this will sort the strings lexicographically (dictionary-order)
        BSTMap<String, Integer> words = new BSTMap<>();
        words.put("ten", 10);
        words.put("five", 5);
        words.put("three", 3);
        System.out.println(words);
    
        // this will sort the strings in reverse lexicographic order
        BSTMap<String, Integer> wordsReverse = new BSTMap<>(new Comparator<String>() {
    
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
    
        });
        wordsReverse.put("ten", 10);
        wordsReverse.put("five", 5);
        wordsReverse.put("three", 3);
        System.out.println(wordsReverse);
    }


}

import java.util.*;

public class BSTMap<K, V> implements MapSet<K, V> {
    private Node<K, V> root;
    private int size;
    private Comparator<K> comparator;

    private static class Node<K, V> extends KeyValuePair<K, V> {
        public Node<K, V> left;
        public Node<K, V> right;
    
        public Node(K key, V value) {
            super(key, value);
            this.left = null;
            this.right = null;
        }
    }

    public BSTMap(Comparator<K> comparator) {
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            this.comparator = new Comparator<K>() {
                public int compare(K key1, K key2) {
                    // replacing compareTo - alr implemented in Constructor
                    // class import
                    return ((Comparable<K>)key1).compareTo(key2);
                }
            };
        }
        this.root = null;
        this.size = 0;
    }

    public BSTMap() {
        // find constructor
        this (null);
    }

    private String toString(Node cur ,int curDepth) {
		if (cur == null){
			return "";
		} else { //Ordering of these call matters for it to be sorted
			String output = this.toString(cur.left, curDepth + 1) ;
			output += "\t".repeat(curDepth) + cur.toString() + "\n";
			output += this.toString(cur.right, curDepth + 1) ;
			return output;
		}
	}

	public String toString() {
		return this.toString( this.root , 0 );
	}

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public V put(K key, V value) {
        if (value != null) {
            return put(key, value, root);
        } else {
            return value;
        }
    }

    private V put(K key, V value, Node<K, V> cur) {
        if (cur == null) {
            root = new Node<K,V>(key, value);
            return null;
        }
        if (comparator.compare(key, cur.getKey()) < 0){
            if (cur.left != null) {
                return put(key, value, cur.left);
            } else {
                cur.left = new Node<K, V>(key, value);
                size++;
                return null;
            }
            
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            if (cur.right != null) {
                return put(key, value, cur.right);
            } else {
                cur.right = new Node<K, V>(key, value);
                size++;
                return null;
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

    private V get(K key, Node<K, V> cur) {
        if (comparator.compare(key, cur.getKey()) == 0){
            return cur.getValue();
        }
        if (comparator.compare(key, cur.getKey()) < 0){
            return get(key, cur.left);
            
        } else if (comparator.compare(key, cur.getKey()) > 0) {
            return get(key, cur.right);
        } else {
            V value = cur.getValue();
            cur.setValue(value);
            return value;
        }
    }

    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node<K, V> cur) {
        if (cur == null) {
            return false;
        }
        int comp = comparator.compare(key, cur.getKey());
        if (comp == 0) {
            return true;
        } else if (comp < 0) {
            return containsKey(key, cur.left);
        } else {
            return containsKey(key, cur.right);
        }
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

    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        entrySet(root, entries);
        return entries;
    }

    private void entrySet(Node<K, V> cur, ArrayList<KeyValuePair<K, V>> output) {
        if (cur == null) {
            return;
        }
        entrySet(cur.left, output);
        output.add(new KeyValuePair<>(cur.getKey(), cur.getValue()));
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
        Node<K, V> toDeleteParent = null;
        Node<K, V> toDelete = root;
    
        while (toDelete != null && !toDelete.getKey().equals(key)) {
            toDeleteParent = toDelete;
            if (comparator.compare(key, toDelete.getKey()) < 0) {
                toDelete = toDelete.left;
            } else {
                toDelete = toDelete.right;
            }
        }
        if (toDelete == null) {
            return null;
        }

        V removedValue = toDelete.getValue();
        handleReplacement(toDelete, toDeleteParent);
        size--;
        return removedValue;
    }

    private void handleReplacement(Node<K, V> toDelete, Node<K, V> toDeleteParent) {
        Node<K, V> replacement;
    
        if (toDelete.left == null) {
            replacement = toDelete.right;
        } else if (toDelete.right == null) {
            replacement = toDelete.left;
        } else {
            Node<K, V> parent = toDelete;
            replacement = toDelete.right;
            while (replacement.left != null) {
                parent = replacement;
                replacement = replacement.left;
            }
            if (parent != toDelete) {
                parent.left = replacement.right;
                replacement.right = toDelete.right;
            }
            replacement.left = toDelete.left;
        }
    
        if (toDeleteParent == null) {
            root = replacement;
        } else if (toDeleteParent.left == toDelete) {
            toDeleteParent.left = replacement;
        } else {
            toDeleteParent.right = replacement;
        }
    }

    @Override
    public V get(K key) {
        return null;
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

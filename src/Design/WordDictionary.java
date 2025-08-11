package Design;

import java.util.Map;
import java.util.TreeMap;

class WordDictionary {
    class Node {
        public TreeMap<Character, Node> next;
        public boolean isend;

        public Node() {
            this(false);
        }

        public Node(boolean isend) {
            next = new TreeMap<>();
            this.isend = isend;
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (temp.next.get(c) == null) {
                temp.next.put(c, new Node());
            }
            temp = temp.next.get(c);
        }
        temp.isend = true;
    }

    public boolean search(String word) {

        return search(root, word, 0);

    }

    private boolean search(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isend;
        }
        char c = word.charAt(index);
        if (c == '.') {
            for (char nextChar : node.next.keySet()) {
                if (search(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
                return false;
            }
        } else {
            if (node.next.get(c) == null) {
                return false;
            }
            return search(node.next.get(c), word, index + 1);
        }
        return false;
    }
}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
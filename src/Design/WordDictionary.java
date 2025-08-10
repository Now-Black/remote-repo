package Design;
import java.util.*;


/**
 * 📚 单词字典类 - 支持添加单词和模糊搜索
 * LeetCode 211: Design Add and Search Words Data Structure
 */
class WordDictionary {

    /**
     * 🌲 Trie树节点类
     */
    class Node {
        // 🎯 使用HashMap存储子节点，替代Python的defaultdict
        Map<Character, Node> children;
        // 🏷️ 标记是否为单词结尾
        boolean isWord;

        /**
         * 🔧 构造函数
         */
        public Node() {
            this.children = new HashMap<>();
            this.isWord = false;
        }
    }
    private Node root;

    /**
     * 🔧 初始化数据结构
     */
    public WordDictionary() {
        this.root = new Node();
    }

    /**
     * 📝 添加单词到数据结构中
     * @param word 要添加的单词
     */
    public void addWord(String word) {
        Node current = root;

        // 🎯 遍历单词的每个字符
        for (char c : word.toCharArray()) {
            // 如果字符对应的子节点不存在，则创建
            current.children.putIfAbsent(c, new Node());
            // 移动到子节点
            current = current.children.get(c);
        }

        // 🏷️ 标记单词结尾
        current.isWord = true;
    }

    /**
     * 🔍 搜索单词是否存在
     * 支持 '.' 通配符，可以匹配任意单个字符
     * @param word 要搜索的单词（可包含'.'）
     * @return 是否找到匹配的单词
     */
    public boolean search(String word) {
        return match(word, 0, root);
    }

    /**
     * 🎯 递归匹配函数
     * @param word 要匹配的单词
     * @param index 当前匹配到的字符索引
     * @param node 当前节点
     * @return 是否匹配成功
     */
    private boolean match(String word, int index, Node node) {
        // 🚫 节点为空，匹配失败
        if (node == null) {
            return false;
        }

        // ✅ 已匹配完整个单词，检查是否为单词结尾
        if (index == word.length()) {
            return node.isWord;
        }

        char currentChar = word.charAt(index);

        // 🎯 如果不是通配符，直接匹配
        if (currentChar != '.') {
            Node childNode = node.children.get(currentChar);
            return childNode != null && match(word, index + 1, childNode);
        }
        // 🌟 如果是通配符'.'，尝试匹配所有可能的子节点
        else {
            for (Node childNode : node.children.values()) {
                if (match(word, index + 1, childNode)) {
                    return true;
                }
            }
            return false;
        }
    }
}

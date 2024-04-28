package colorado.wordle.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;

@Slf4j
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'A';  
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;  
    }

    public boolean search(String word) {
        TrieNode node = getNode(word);
        return node != null && node.isEndOfWord;
    }

    public List<String> contains(String sequence) {
        List<String> results = new ArrayList<>();
        sequence = sequence.toUpperCase();
        List<String> permutations = new ArrayList<>();
        findSequence(root, "", sequence, results);
        return results;
    }

    private void findSequence(TrieNode current, String currentWord, String sequence, List<String> results) {
        if (current == null) {
            return;
        }
        if (currentWord.contains(sequence) && current.isEndOfWord) {
            results.add(currentWord);
        }
        for (int i = 0; i < 26; i++) {
            if (current.children[i] != null) {
                char nextChar = (char) (i + 'A');
                findSequence(current.children[i], currentWord + nextChar, sequence, results);
            }
        }
    }

    public List<String> searchPattern(String pattern) {
        List<String> results = new ArrayList<>();
        searchTrie(root, pattern, 0, "", results);
        return results;
    }

    private void searchTrie(TrieNode node, String pattern, 
                    int index, String currentWord, List<String> results) {
        if (node == null) {
            return;
        }
    
        if (index == pattern.length()) {
            if (node.isEndOfWord) {
                //log.info("adding word: {}", currentWord);
                results.add(currentWord);
            }

            return;
        }
    
        char currentChar = pattern.charAt(index);
    
        if (currentChar == '_') {
            for (char c = 'A'; c <= 'Z'; c++) {
                if (node.children[c - 'A'] != null) {
                    searchTrie(node.children[c - 'A'], pattern, index + 1, currentWord + c, results);
                }
            }
        } else {
            if (node.children[currentChar - 'A'] != null) {
                searchTrie(node.children[currentChar - 'A'], pattern, index + 1, currentWord + currentChar, results);
            }
        }
    }

    public boolean startsWith(String prefix) {
        TrieNode node = getNode(prefix);
        return node != null;
    }

    private TrieNode getNode(String s) {
        TrieNode current = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}

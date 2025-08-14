package org.example.bst;

import java.text.CollationElementIterator;
import java.util.*;

public class BSTBuilder {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public Node buildTree(List<Integer> numbers) {
        Node root = null;
        for (int num : numbers) {
            root = insertIteratively(root, num);
        }
        return root;
    }

    private Node insertIteratively(Node root, int val) {
        Node newNode = new Node(val);
        if (root == null) return newNode;

        Node current = root;
        while (true) {
            if (val < current.value) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                }
                current=current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            }
        }
        return root;
    }

    public Map<String, Object> toJson(Node root) {
        if (root == null) return Collections.emptyMap();

        Map<String, Object> result = new LinkedHashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Map<String, Object>> nodeMap = new HashMap<>();

        queue.add(root);
        Map<String, Object> rootMap = new LinkedHashMap<>();
        rootMap.put("value", root.value);
        nodeMap.put(root, rootMap);
        result = rootMap;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Map<String, Object> nodeJson = nodeMap.get(node);

            if (node.left != null) {
                Map<String, Object> leftJson = new LinkedHashMap<>();
                leftJson.put("value", node.left.value);
                nodeJson.put("left", leftJson);
                nodeMap.put(node.left, leftJson);
                queue.add(node.left);
            }
            if (node.right != null) {
                Map<String, Object> rightJson = new LinkedHashMap<>();
                rightJson.put("value", node.right.value);
                nodeJson.put("right", rightJson);
                nodeMap.put(node.right, rightJson);
                queue.add(node.right);
            }
        }
        return result;
    }
}

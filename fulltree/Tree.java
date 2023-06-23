package fulltree;

public class Tree<T extends Comparable<T>> {
    private Node root;
    
    public boolean add(T value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.Black;
            return true;
        }
        return addNode(root, value);
    }
    
    private boolean addNode(Node node, T value) {
        if (node.value.compareTo(value) == 0) return false;
        if (node.value.compareTo(value) > 0) {
            if (node.left != null) {
                boolean result = addNode(node.left, value);
                node.left = rebalanced(node.left);
                return result;
            } else {
                node.left = new Node();
                node.left.color = Color.Red;
                node.left.value = value;
                return true;
            }
        } else {
            if (node.right != null) {
                boolean result = addNode(node.right, value);
                node.right = rebalanced(node.right);
                return result;
            } else {
                node.right = new Node();
                node.right.color = Color.Red;
                node.right.value = value;
                return true;
            }
        }
    }
    
    public boolean contain(T value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) return true;
            if (currentNode.value.compareTo(value) > 0) currentNode = currentNode.left;
            else currentNode = currentNode.right;
        }
        return false;
    }
    
    private Node rebalanced(Node node) {
        if (node == null) return null;
        if (node.left != null && node.left.color == Color.Red) {
            if (node.left.left != null && node.left.left.color == Color.Red) {
                return rotateRight(node);
            } else if (node.left.right != null && node.left.right.color == Color.Red) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (node.right != null && node.right.color == Color.Red) {
            if (node.right.left != null && node.right.left.color == Color.Red) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            } else if (node.right.right != null && node.right.right.color == Color.Red) {
                return rotateLeft(node);
            }
        }
        return node;
    }
    
    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = Color.Red;
        return right;
    }
    
    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = Color.Red;
        return left;
    }
    
    
    private enum Color {
        Red,
        Black
    }
    
    private class Node {
        T value;
        Node left;
        Node right;
        Color color;
    }
}

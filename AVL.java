import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Node {
    int data;
    int h;
    Node leftChild;
    Node rightChild;

    public Node() {
        data = 0;
        h = 0;
        leftChild = null;
        rightChild = null;
    }

    public Node(int value) {
        this.data = value;
        h = 0;
        leftChild = null;
        rightChild = null;
    }
}

class ConstructAvlTree {
    private Node root;

    public ConstructAvlTree() {
        root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public int getHeight(Node node) {
        return node == null ? -1 : node.h;
    }

    private int max(int l, int r) {
        return l > r ? l : r;
    }

    private Node insert(Node node, int value) {
        if (node == null)
            node = new Node(value);
        else if (value < node.data) {
            node.leftChild = insert(node.leftChild, value);
            if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2) {
                if (value < node.leftChild.data)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
            }
        } else if (value > node.data) {
            node.rightChild = insert(node.rightChild, value);
            if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2) {
                if (value > node.rightChild.data)
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);
            }
        }
        node.h = max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return node;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null)
            return null;

        if (value < node.data) {
            node.leftChild = delete(node.leftChild, value);
        } else if (value > node.data) {
            node.rightChild = delete(node.rightChild, value);
        } else {
            if (node.leftChild == null || node.rightChild == null) {
                node = (node.leftChild == null) ? node.rightChild : node.leftChild;
            } else {
                Node temp = findMin(node.rightChild);
                node.data = temp.data;
                node.rightChild = delete(node.rightChild, temp.data);
            }
        }

        if (node == null)
            return node;

        node.h = max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;

        int balance = getHeight(node.leftChild) - getHeight(node.rightChild);

        // Left heavy
        if (balance > 1) {
            if (getHeight(node.leftChild.leftChild) >= getHeight(node.leftChild.rightChild)) {
                return rotateWithLeftChild(node);
            } else {
                return doubleWithLeftChild(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            if (getHeight(node.rightChild.rightChild) >= getHeight(node.rightChild.leftChild)) {
                return rotateWithRightChild(node);
            } else {
                return doubleWithRightChild(node);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = max(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
        node1.h = max(getHeight(node1.leftChild), node2.h) + 1;
        return node1;
    }

    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = max(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
        node2.h = max(getHeight(node2.rightChild), node1.h) + 1;
        return node2;
    }

    private Node doubleWithLeftChild(Node node3) {
        node3.leftChild = rotateWithRightChild(node3.leftChild);
        return rotateWithLeftChild(node3);
    }

    private Node doubleWithRightChild(Node node1) {
        node1.rightChild = rotateWithLeftChild(node1.rightChild);
        return rotateWithRightChild(node1);
    }

    public void inorderTraversalToFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        inorderTraversalToFile(root, sb);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error Writing to file: " + e.getMessage());
        }
    }

    private void inorderTraversalToFile(Node node, StringBuilder sb) {
        if (node != null) {
            inorderTraversalToFile(node.leftChild, sb);
            sb.append(node.data).append("\n");
            inorderTraversalToFile(node.rightChild, sb);
        }
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.leftChild);
            System.out.print(node.data + " ");
            inorderTraversal(node.rightChild);
        }
    }

    public void search(int key) {
        Node n = search(root, key);
        if (n == null)
            System.out.println("Key is not found in tree");
        else
            System.out.println("Key is found");
    }

    private Node search(Node node, int key) {
        if (node == null || node.data == key)
            return node;
        else if (key < node.data)
            return search(node.leftChild, key);
        else
            return search(node.rightChild, key);
    }
}

public class AVL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConstructAvlTree avl = new ConstructAvlTree();
        while (true) {
            System.out.println("1. Insert an element");
            System.out.println("2. Write Inorder Traversal to file");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Delete a number");
            System.out.println("5. Search for an element");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter element:");
                    int item = sc.nextInt();
                    avl.insert(item);
                    break;
                case 2:
                    System.out.println("Enter file name to save Inorder Traversal:");
                    String fileName = sc.next();
                    avl.inorderTraversalToFile(fileName);
                    System.out.println("Inorder traversal written to " + fileName);
                    break;
                case 3:
                    System.out.println("Print Inorder Traversal:");
                    avl.inorderTraversal();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Delete a number:");
                    int deleteValue = sc.nextInt();
                    avl.delete(deleteValue);
                    System.out.println("Deleted");
                    break;
                case 5:
                    System.out.println("Enter the key element to search:");
                    int key = sc.nextInt();
                    avl.search(key);
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}

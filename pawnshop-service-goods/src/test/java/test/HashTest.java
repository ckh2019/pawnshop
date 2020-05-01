package test;

import org.junit.Test;

/**
 * @author Chen Kaihong
 * 2020-03-28 13:06
 */

public class HashTest {

    @Test
    public void test1 () {

        HashTab hashTab = new HashTab();
       /* hashTab.add(new Node(9));
        hashTab.add(new Node(1));
        hashTab.add(new Node(3));
        hashTab.add(new Node(15));
        hashTab.add(new Node(0));
        hashTab.add(new Node(6));
        hashTab.add(new Node(12));
        hashTab.add(new Node(18));
        hashTab.add(new Node(2));
        hashTab.add(new Node(10));
        hashTab.add(new Node(7));
        hashTab.add(new Node(13));
        hashTab.add(new Node(15));*/
        hashTab.add(new Node(1));
        hashTab.add(new Node(4));
        hashTab.add(new Node(7));
        hashTab.add(new Node(10));
        hashTab.add(new Node(13));
        //hashTab.trees[1].midOrder(hashTab.trees[1].root);
        System.out.println(hashTab.trees[1].root);

    }

    @Test
    public void test2() {
        Tree tree = new Tree();
        tree.add(new Node(1));
        tree.add(new Node(2));
        tree.add(new Node(3));
        tree.add(new Node(4));
        tree.add(new Node(5));
        tree.add(new Node(6));
        System.out.println(tree.root.height());




    }
}
class HashTab {
    Tree[] trees;

    public HashTab() {
        trees = new Tree[3];
        for (int i = 0; i < trees.length; i ++) {
            trees[i] = new Tree();
        }
    }

    public void add(Node node) {
        int index = hashCode(node.id);
        Tree tree = trees[index];
        tree.add(node);
    }

    public Node search (int id) {
        int index = hashCode(id);
        Tree tree = trees[index];
        return tree.search(tree.root, id);
    }

    private int hashCode (int id) {
        return id%trees.length;
    }
}
class Node {
    int id;
    Node left;
    Node right;

    public Node(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return id+ " ";
    }
    public int height() {
        return Math.max(left == null?0:left.height(), right == null? 0: right.height()) + 1;
    }

    private int leftHeight() {
        return left == null ? 0 : left.height();
    }

    private int rightHeight () {
        return right == null ? 0 : right.height();
    }

    public void add(Node node) {
        if (this.id > node.id) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else if (this.id < node.id) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
        }
        if (leftHeight() - rightHeight() < -1) {
            if (right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
        }
    }

    private void leftRotate() {
        Node node = new Node(id);
        node.left = left;
        node.right = right.left;
        id = right.id;
        right = right.right;
        left = node;
    }

    private void rightRotate() {
        Node node = new Node(id);
        node.left = left.right;
        node.right = right;
        id = left.id;
        left = left.left;
        right = node;
    }
}
class Tree {

    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root);
        }
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    public void midOrder(Node root) {
        if (root != null) {
            if (root.left != null) {
                midOrder(root.left);
            }
            System.out.print(root);
            if (root.right != null) {
                midOrder(root.right);
            }
        }
    }

    public Node search(Node root, int id) {
        if (root != null) {
            if (root.id == id) {
                return root;
            } else if (root.id > id && root.left != null) {
                return search(root.left, id);
            } else if (root.id < id && root.right != null) {
                return search(root.right, id);
            }
        }
        return null;
    }




}
package org.gallant.interview;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Gallant
 */
@Getter
@Setter
public class TreeNode<T> {

  private T data;
  private TreeNode<T> up;
  private TreeNode<T> down;
  private TreeNode<T> left;
  private TreeNode<T> right;

  public static void main(String[] args) {
    TreeNode<Integer>[][] result = buildGraph(4);
    System.out.println("end----");
    traverse(result, 3, 3);
  }

  private static void traverse(TreeNode<Integer>[][] nodes, int startX, int startY) {
    TreeNode<Integer> start = nodes[startX][startY];
    traverseLeftDownSide(start, true);
    traverseLeftDownSide(start, false);
  }
  private static void traverseLeftDownSide(TreeNode<Integer> node, boolean leftDown) {
    TreeNode<Integer> current = node;
    int i = leftDown ? 0 : 1;
    if (leftDown) {
      System.out.print(node.data + "\t");
    }
    while (current != null) {
      TreeNode<Integer> temp = (i % 2 == 0 ? current.getLeft() : current.getRight());
      if (temp == null) {
        current = leftDown ? current.getDown() : current.getUp();
        i++;
        System.out.println();
      } else {
        current = temp;
      }
      if (current != null) {
        System.out.print(current.getData() + "\t");
      }
    }
  }

  private static TreeNode<Integer>[][] buildGraph(int n) {
    TreeNode<Integer>[][] nodes = new TreeNode[n][n];
    int count = 1;
    for (int i = 0; i < n; i++) {
      for (int i1 = 0; i1 < n; i1++) {
        TreeNode<Integer> current = new TreeNode<>();
        current.setData(count++);
        nodes[i][i1] = current;
        if (i1 - 1 >= 0) {
          TreeNode<Integer> left = nodes[i][i1-1];
          if (left != null) {
            left.right = current;
            current.left = left;
          }
        }
        if (i - 1 >= 0) {
          TreeNode<Integer> up = nodes[i-1][i1];
          if (up != null) {
            up.down = current;
            current.up = up;
          }
        }
        if (i1 + 1 < nodes[i].length) {
          TreeNode<Integer> right = nodes[i][i1 + 1];
          if (right != null) {
            right.left = current;
            current.right = right;
          }
        }
        if (i + 1 < nodes.length) {
          TreeNode<Integer> down = nodes[i+1][i1];
          if (down != null) {
            current.down = down;
            down.up = current;
          }
        }
      }
    }
    return nodes;
  }

}

package org.gallant.leetcode.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.gallant.leetcode.algorithms.domain.UnionSetNode;

/**
 * @author : 会灰翔的灰机
 * @date : 2021/8/22
 */
public class Q200NumIslands {

  private static Map<String, Set<UnionSetNode>> unionSet = new HashMap<>();
  private static final String UNION_SET_NODE_KEY = "x:%s,y:%s";

  /**
   * 初始化并查集
   * @param data ： 输入数据集合
   */
  private static void makeSet(int[][] data) {
    unionSet = new HashMap<>(data.length + data[0].length);
    for (int i = 0; i < data.length; i++) {
      int[] row = data[i];
      for (int i1 = 0; i1 < row.length; i1++) {
        int value = row[i1];
        if (value == 1) {
          String unionSetKey = getUnionSetNodeKey(i1, i);
          unionSet.putIfAbsent(unionSetKey, new HashSet<>());
          Set<UnionSetNode> unionSetNodes = unionSet.get(unionSetKey);
          UnionSetNode unionSetNode = new UnionSetNode();
          unionSetNode.setY(i);
          unionSetNode.setX(i1);
          unionSetNode.setValue(value);
          unionSetNodes.add(unionSetNode);
        }
      }
    }
  }

  /**
   * 查找根节点
   * @param unionSetNode ： 输入节点
   * @return ： 根节点
   */
  private static UnionSetNode find(UnionSetNode unionSetNode) {
    if (unionSetNode.getParent() == null) {
      return unionSetNode;
    }
    return find(unionSetNode.getParent());
  }

  /**
   * 合并并查集：node2合并至node1节点
   * @param node1 ：
   * @param node2 ：
   */
  private static void union(UnionSetNode node1, UnionSetNode node2) {
    UnionSetNode root1 = find(node1);
    UnionSetNode root2 = find(node2);
    if (root1 != root2) {
      root2.setParent(root1);
    }
  }

  /**
   * 岛屿数量
   * @param data ： 数据集合
   * @return ： 岛屿数量
   */
  private static int numIslands(int[][] data) {
    makeSet(data);
    unionSet.forEach((k, v) -> {
      UnionSetNode currentRoot = find(v.iterator().next());
      int x = currentRoot.getX();
      int y = currentRoot.getY();
      int leftX = x - 1;
      Set<UnionSetNode> leftNodes = unionSet.get(getUnionSetNodeKey(leftX, y));
      // 如果存在左邻的陆地，则合并
      if (leftNodes != null) {
        UnionSetNode leftRoot = find(leftNodes.iterator().next());
        union(leftRoot, currentRoot);
      }
      // 如果存在右邻的陆地，则合并
      int rightX = x + 1;
      Set<UnionSetNode> rightNodes = unionSet.get(getUnionSetNodeKey(rightX, y));
      if (rightNodes != null) {
        UnionSetNode rightRoot = find(rightNodes.iterator().next());
        union(currentRoot, rightRoot);
      }
      // 如果存在上邻的陆地，则合并
      int upY = y - 1;
      Set<UnionSetNode> upNodes = unionSet.get(getUnionSetNodeKey(x, upY));
      if (upNodes != null) {
        UnionSetNode upRoot = find(upNodes.iterator().next());
        union(upRoot, currentRoot);
      }
      // 如果存在下邻的陆地，则合并
      int downY = y + 1;
      Set<UnionSetNode> downNodes = unionSet.get(getUnionSetNodeKey(x, downY));
      if (downNodes != null) {
        UnionSetNode downRoot = find(downNodes.iterator().next());
        union(currentRoot, downRoot);
      }
    });
    Set<UnionSetNode> roots = new HashSet<>();
    unionSet.forEach((k, v) -> roots.add(find(v.iterator().next())));
    return roots.size();
  }

  public static void main(String[] args) {
    String example1 = "[\n"
        + "  [\"1\",\"1\",\"1\",\"1\",\"0\"],\n"
        + "  [\"1\",\"1\",\"0\",\"1\",\"0\"],\n"
        + "  [\"1\",\"1\",\"0\",\"0\",\"0\"],\n"
        + "  [\"0\",\"0\",\"0\",\"0\",\"0\"]\n"
        + "]";
    int[][] data1 = initData(example1);
    printArray(data1);
    // 所有陆地集合初始化
    makeSet(data1);
    printUnionSet();
    // 岛屿数量
    System.out.println(numIslands(data1));
    printUnionSet();
    System.out.println("---------------------");

    String example2 = "[\n"
        + "  [\"1\",\"1\",\"0\",\"0\",\"0\"],\n"
        + "  [\"1\",\"1\",\"0\",\"0\",\"0\"],\n"
        + "  [\"0\",\"0\",\"1\",\"0\",\"0\"],\n"
        + "  [\"0\",\"0\",\"0\",\"1\",\"1\"]\n"
        + "]";
    int[][] data2 = initData(example2);
    printArray(data2);
    // 所有陆地集合初始化
    makeSet(data2);
    // 岛屿数量
    System.out.println(numIslands(data2));
  }

  private static String getUnionSetNodeKey(int x, int y) {
    return String.format(UNION_SET_NODE_KEY, x, y);
  }

  private static int[][] initData(String dataStr) {
    int[][] data = new int[4][5];
    int i=0;
    String[] line = dataStr.split("\n");
    for (String s : line) {
      if (s.length() == 1) {
        continue;
      }
      String row = s.replaceAll("[\\[\\]]", "");
      row = row.substring(0, row.length() - 1);
      String[] cols = row.split(",");
      int j=0;
      for (String col : cols) {
        data[i][j++] = Integer.parseInt(col.replace("\"", "").trim());
      }
      i++;
    }
    return data;
  }

  private static void printArray(int[][] data) {
    for (int[] datum : data) {
      for (int i : datum) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  private static void printUnionSet() {
    unionSet.forEach((k, v) -> System.out.println(k + "," + v));
  }

}

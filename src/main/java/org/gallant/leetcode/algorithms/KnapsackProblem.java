package org.gallant.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 背包问题
 *
 * @author : 会灰翔的灰机
 * @date : 2021/8/28
 */
public class KnapsackProblem {

  public static void main(String[] args) {
    // 案例1
    int[] weights = new int[]{3, 1, 5, 7, 8};
    int[] values = new int[] {5, 3, 7, 9, 10};
//    exhaustivity(weights, values);
    // 遍历每个物品，计算包含物品与不包含物品的最大价值
    // 根据输出结果选择最大价值组合
    /*为什么需要遍历所有物品（3,1,5,7,8）计算最大价值？

    dynamicPrograming4KnapsackProblem代码实现每次仅判断了是否包含物品3的场景，不包含是否包含其他物品的场景。
    假设仅遍历一边物品3，那么得到的结论是：最大价值包含物品3，或不包含。但是对于物品1没有判断是否包含，物品3包含或不包含两个代码分支可能都包含物品1，因此需要遍历计算所有物品，然后选择大价值价值组合*/
    for (int i = 0; i < weights.length; i++) {
      if (i != 0) {
        exchange(weights, values, i, 0);
      }
      PrintUtil.printArray(weights);
      PrintUtil.printArray(values);
      dynamicPrograming4KnapsackProblem(weights, values, 0, 15, 15, 0, 0, new ArrayList<>());
      System.out.println("----------------");
    }
    System.out.println("######################案例2######################");
    // 案例2
    weights = new int[]{3, 1, 5, 7, 8};
    values = new int[] {5, 3, 7, 9, 10};
    for (int i = 0; i < weights.length; i++) {
      if (i != 0) {
        exchange(weights, values, i, 0);
      }
      PrintUtil.printArray(weights);
      PrintUtil.printArray(values);
      dynamicPrograming4KnapsackProblem(weights, values, 0, 12, 12, 0, 0, new ArrayList<>());
      System.out.println("----------------");
    }
  }

  /**
   * 计算最大价值中是否包含索引0处的物品
   *
   * @param weights 所有物品对应的重量
   * @param values 所有物品对应的价值
   * @param index 当前遍历索引
   * @param finalThreshold  最大承重阈值
   * @param threshold 当前剩余承重阈值
   * @param summaryWeight 总重量
   * @param summaryValue  总价值
   * @param indexes 遍历过的索引列表
   * @return ： 最大价值
   */
  private static int dynamicPrograming4KnapsackProblem(int[] weights, int[] values, int index, int finalThreshold, int threshold, int summaryWeight, int summaryValue, List<Integer> indexes) {
    if (index >= weights.length) {
      return summaryValue;
    }
    int w = weights[index];
    int v = values[index];
    // 达到背包最大承重，结束遍历
    if (summaryWeight + w > finalThreshold) {
      System.out.printf("sw:%s,sv:%s,%s%n", summaryWeight, summaryValue, indexes);
      indexes.clear();
      return summaryValue;
    }
    List<Integer> indexesOld = new ArrayList<>(indexes);
    indexes.add(index);
    // 包含当前物品的最大价值
    int maxValue1 = dynamicPrograming4KnapsackProblem(weights, values, index + 1, finalThreshold, threshold - w, summaryWeight + w, summaryValue + v, indexes);
    // 不包含当前物品的最大价值
    int maxValue2 = dynamicPrograming4KnapsackProblem(weights, values, index + 1, finalThreshold, threshold, summaryWeight, summaryValue, indexesOld);
    if (maxValue1 > maxValue2) {
      return maxValue1;
    } else {
      return maxValue2;
    }
  }

  private static void exchange(int[] weights, int[] values, int i, int j) {
    int hw = weights[j];
    int hv = values[j];
    weights[j] = weights[i];
    values[j] = values[i];
    weights[i] = hw;
    values[i] = hv;
  }

  private static void binary(int[] weights) {
    int base = 2;
    int length = weights.length;
    int threshold = (int) Math.pow(base, length);
    for (int i = 0; i < threshold; i++) {
      StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
      if (binaryString.length() < length) {
        int diffLen = length - binaryString.length();
        for (int j = 0; j < diffLen; j++) {
          binaryString.insert(0, "0");
        }
      }
      List<Integer> indexes = Arrays.stream(binaryString.toString().split("")).map(Integer::parseInt).collect(Collectors.toList());
      System.out.println(indexes);
    }
  }

  private static void graph(int[] weights) {
    int[][] graph = new int[5][5];
    for (int i = 0; i < graph.length; i++) {
      int[] edge = graph[i];
      for (int i1 = 0; i1 < edge.length; i1++) {
        edge[i1] = weights[i1];
      }
      if (i != 0) {
        int header = edge[0];
        int newHeader = edge[i];
        edge[0] = newHeader;
        edge[i] = header;
      }
    }
    PrintUtil.printGraph(graph);
  }

  private static void exhaustivity(int[] weights, int[] values) {
    // 01背包问题，每件物品均有两种选择，放入背包，或不放入背包
    int size = 2;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        for (int k = 0; k < size; k++) {
          for (int l = 0; l < size; l++) {
            for (int m = 0; m < size; m++) {
              int summaryWeight = 0, summaryValue =0;
              summaryWeight += i * weights[0];
              summaryValue += i * values[0];
              summaryWeight += j * weights[1];
              summaryValue += j * values[1];
              summaryWeight += k * weights[2];
              summaryValue += k * values[2];
              summaryWeight += l * weights[3];
              summaryValue += l * values[3];
              summaryWeight += m * weights[4];
              summaryValue += m * values[4];
              System.out.printf("i:%s,j:%s,k:%s,l:%s,m:%s,summaryWeight:%s,summaryValue:%s\n", i, j, k, l, m, summaryWeight, summaryValue);
            }
          }
        }
      }
    }
  }

}

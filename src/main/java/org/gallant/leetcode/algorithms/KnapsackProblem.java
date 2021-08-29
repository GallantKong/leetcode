package org.gallant.leetcode.algorithms;

import java.util.List;

/**
 * 背包问题
 *
 * @author : 会灰翔的灰机
 * @date : 2021/8/28
 */
public class KnapsackProblem {

  public static void main(String[] args) {
    int[] weights = new int[]{3, 1, 5, 7, 8};
    int[] values = new int[] {5, 3, 7, 9, 10};
//    exhaustivity(weights, values);
    int threshold = 15;
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

  private static void dynamicPrograming4KnapsackProblem(List<Integer> ws, List<Integer> vs, int threshold) {

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
              if (i != 0) {
                summaryWeight += weights[0];
                summaryValue += values[0];
              }
              if (j != 0) {
                summaryWeight += weights[1];
                summaryValue += values[1];
              }
              if (k != 0) {
                summaryWeight += weights[2];
                summaryValue += values[2];
              }
              if (l != 0) {
                summaryWeight += weights[3];
                summaryValue += values[3];
              }
              if (m != 0) {
                summaryWeight += weights[4];
                summaryValue += values[4];
              }
              System.out.printf("i:%s,j:%s,k:%s,l:%s,m:%s,summaryWeight:%s,summaryValue:%s\n", i, j, k, l, m, summaryWeight, summaryValue);
            }
          }
        }
      }
    }
  }

}

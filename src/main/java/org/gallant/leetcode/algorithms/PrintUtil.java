package org.gallant.leetcode.algorithms;

/**
 * @author : kongyong
 * @date : 2021/8/29
 */
public class PrintUtil {

  public static void printGraph(int[][] graph) {
    for (int[] ints : graph) {
      for (int anInt : ints) {
        System.out.print(anInt+",");
      }
      System.out.println();
    }
  }

}

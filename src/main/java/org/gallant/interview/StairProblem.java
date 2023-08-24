package org.gallant.interview;

/**
 * @author Gallant
 */
public class StairProblem {

  public static void main(String[] args) {
    int stairNum = 3;
    System.out.println(count(stairNum));
  }

  private static int count(int n) {
    if (n <= 0) {
      return 0;
    }
    if (n == 1) {
      return n;
    }
    return 2 * (count(n - 1) + count(n - 2) + count(n - 3));
  }

}

package org.gallant.interview;

/**
 * @author Gallant
 */
public class StairProblem {

  public static void main(String[] args) {
    int stairNum = 4;
    System.out.println(count(stairNum));
  }

  private static int count(int n) {
    if (n < 2) {
      return 0;
    }
    if (n == 2) {
      return 1;
    }
    return 2 * (count(n - 1) + count(n - 2) + count(n - 3));
  }

}

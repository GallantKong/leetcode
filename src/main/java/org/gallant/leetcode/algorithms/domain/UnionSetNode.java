package org.gallant.leetcode.algorithms.domain;

/**
 * @author : 会灰翔的灰机
 * @date : 2021/8/22
 */
public class UnionSetNode {

  /**
   * 父节点
   */
  private UnionSetNode parent;

  /**
   * 横坐标
   */
  private int x;
  /**
   * 纵坐标
   */
  private int y;
  /**
   * 节点值
   */
  private int value;

  public UnionSetNode getParent() {
    return parent;
  }

  public void setParent(UnionSetNode parent) {
    this.parent = parent;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    return String.format("%s%s", x, y).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof UnionSetNode)) {
      return false;
    }
    UnionSetNode input = (UnionSetNode) obj;
    return x == input.getX() && y == input.getY();
  }

  @Override
  public String toString() {
    return "UnionSetNode{" +
        (parent == null ? null : "parent=" + parent.getX() + ":" + parent.getY()) +
        ", x=" + x +
        ", y=" + y +
        ", value=" + value +
        '}';
  }
}

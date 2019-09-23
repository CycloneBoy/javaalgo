package com.cycloneboy.algo.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Create by sl on 2019-09-23 22:39 */
@NoArgsConstructor
public class BinarySearchTree<T extends Comparable<? super T>> {

  private BinaryNode<T> root;

  public void makeEmpty() {
    root = null;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public boolean contains(T x) {
    return contains(x, root);
  }

  public T findMin() throws UnderflowException {
    if (isEmpty()) {
      throw new UnderflowException();
    }

    return findMin(root).element;
  }

  public T findMax() throws UnderflowException {
    if (isEmpty()) {
      throw new UnderflowException();
    }

    return findMax(root).element;
  }

  public void insert(T x) {
    root = insert(x, root);
  }

  public void remove(T x) {
    root = remove(x, root);
  }

  public void printTree() {}

  /**
   * Internal method to find an item in a subtree
   *
   * @param x is item to search for
   * @param root the node that the subtree
   * @return true if the item is found; false otherwise9
   */
  private boolean contains(T x, BinaryNode<T> root) {
    if (x == null) {
      return false;
    }

    int compareResult = x.compareTo(root.element);

    if (compareResult < 0) {
      return contains(x, root.left);
    } else if (compareResult > 0) {
      return contains(x, root.right);
    } else {
      return true;
    }
  }

  /**
   * Internal method to find the smallest item in a subtree
   *
   * @param root the node that roots the subtree
   * @return node containing the smallest item.
   */
  private BinaryNode<T> findMin(BinaryNode<T> root) {
    if (root == null) {
      return null;
    } else if (root.left == null) {
      return root;
    }
    return findMin(root.left);
  }

  /**
   * Internal method to find the largest item in a subtree
   *
   * @param root the node that roots the subtree
   * @return node containing the largest item.
   */
  private BinaryNode<T> findMax(BinaryNode<T> root) {
    if (root != null) {
      while (root.right != null) {
        root = root.right;
      }
    }
    return root;
  }

  /**
   * Internal method to insert into a subtree
   *
   * @param x the item to insert
   * @param root the node that roots the subtree
   * @return the new root of the subtree;
   */
  private BinaryNode<T> insert(T x, BinaryNode<T> root) {
    if (root == null) {
      return new BinaryNode<>(x, null, null);
    }

    int compareResult = x.compareTo(root.element);

    if (compareResult < 0) {
      root.left = insert(x, root.left);
    } else if (compareResult > 0) {
      root.right = insert(x, root.right);
    }

    return root;
  }

  /**
   * Internal method to remove into a subtree
   *
   * @param x the item to remove
   * @param root the node that roots the subtree
   * @return the new root of the subtree;
   */
  private BinaryNode<T> remove(T x, BinaryNode<T> root) {
    if (root == null) {
      return root;
    }

    int compareResult = x.compareTo(root.element);

    if (compareResult < 0) {
      root.left = remove(x, root.left);
    } else if (compareResult > 0) {
      root.right = remove(x, root.right);
    } else if (root.left != null && root.right != null) {
      // Two children
      root.element = findMin(root.right).element;
      root.right = remove(root.element, root.right);
    } else {
      root = (root.left != null) ? root.left : root.right;
    }

    return root;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  private static class BinaryNode<T> {

    T element;
    BinaryNode<T> left;
    BinaryNode<T> right;

    BinaryNode(T element) {
      this(element, null, null);
    }
  }
}

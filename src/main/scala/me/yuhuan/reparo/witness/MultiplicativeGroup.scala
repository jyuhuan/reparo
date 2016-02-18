package me.yuhuan.reparo.witness

/**
 * Represents a group in which the operation has the meaning of multiplication.
 *
 * Essentially, this is a [[lea.algebra.MultiplicativeMonoid MultiplicativeMonoid]] with inverses).
 *
 * @tparam T The type of the elements in the group.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait MultiplicativeGroup[T] extends MultiplicativeMonoid[T] {
  /**
   * Calculates the multiplicative inverse of the given element.
   * @param x An element in the group.
   * @return The multiplicative inverse (i.e., '''rec'''iprocal) of the given element.
   */
  def rec(x: T): T
}

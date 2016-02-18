package me.yuhuan.reparo.witness

/**
 * Represents a group.
 *
 * A group is a [[lea.algebra.Monoid Monoid]] with inverses.
 *
 * @tparam T The type of the elements in the semigroup.
 */
trait Group[T] extends Monoid[T] {
  /**
   * Calculates the inverse of the given element.
   * @param x An element in the group.
   * @return The inverse of the given element.
   */
  def inv(x: T): T
}

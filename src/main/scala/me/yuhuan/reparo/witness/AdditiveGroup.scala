package me.yuhuan.reparo.witness

/**
 * Represents a group in which the operation has the meaning of addition.
 *
 * Essentially, this is an [[lea.algebra.AdditiveMonoid AdditiveMonoid]] with inverses).
 *
 * @tparam T The type of the elements in the group.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait AdditiveGroup[T] extends AdditiveMonoid[T] {
  /**
   * Calculates the additive inverse of the given element.
   * @param x An element in the group.
   * @return The additive inverse (i.e., '''neg'''ative) of the given element.
   */
  def neg(x: T): T
}

package me.yuhuan.reparo.witness

/**
 * Represents a monoid in which the operation has the meaning of addition.
 *
 * Essentially, this is an [[lea.algebra.AdditiveSemigroup AdditiveSemigroup]] with
 * identity (i.e., zero)
 *
 * @tparam T The type of the elements in the monoid.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait AdditiveMonoid[T] extends AdditiveSemigroup[T] {
  /**
   * The additive identity (i.e., zero) of the monoid
   * @return The additive identity (i.e., zero) of the monoid
   */
  def zero: T
}

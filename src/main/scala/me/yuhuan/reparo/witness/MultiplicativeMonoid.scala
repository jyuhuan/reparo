package me.yuhuan.reparo.witness

/**
 * Represents a monoid in which the operation has the meaning of multiplication.
 *
 * Essentially, this is an [[lea.algebra.MultiplicativeSemigroup MultiplicativeSemigroup]] with identity (i.e., zero)
 *
 * @tparam T The type of the elements in the monoid.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait MultiplicativeMonoid[T] extends MultiplicativeSemigroup[T] {
  /**
   * The multiplicative identity (i.e., one) of the monoid.
   * @return The additive identity (i.e., zero) of the monoid
   */
  def one: T
}

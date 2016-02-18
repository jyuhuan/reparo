package me.yuhuan.reparo.witness

/**
 * Represents a monoid.
 *
 * A monoid is a [[lea.algebra.Semigroup Semigroup]] with an identity element.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 *
 */
trait Monoid[T] extends Semigroup[T] {
  /**
   * The identity element.
   */
  def id: T
}

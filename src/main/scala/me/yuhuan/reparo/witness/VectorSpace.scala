package me.yuhuan.reparo.witness

/**
 * Represents a vector space.
 *
 * A vector space is defined over a field S (scalar's type).
 *
 * @tparam V Vector's type.
 * @tparam S Scalar's type.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait VectorSpace[V, S] extends AdditiveGroup[V] {
  def mul(s: S, t: V): V
  def one: S
}

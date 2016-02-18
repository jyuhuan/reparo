package me.yuhuan.reparo.witness

/**
 * Represents a semigroup.
 *
 * A semigroup is a set with an associative operation.
 *
 * @tparam T The type of the elements in the semigroup.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait Semigroup[T] {
  /**
   * The associative binary operation.
   * @param a The first operand.
   * @param b The second operand.
   * @return The result of the operation.
   */
  def op(a: T, b: T): T
}

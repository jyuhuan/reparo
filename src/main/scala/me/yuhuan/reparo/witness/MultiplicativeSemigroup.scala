package me.yuhuan.reparo.witness

/**
 * Represents a semigroup in which the operation has the meaning of multiplication.
 *
 * @tparam T The type of the elements in the semigroup.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait MultiplicativeSemigroup[T] {
  /**
   * The operation of this semigroup. It has the meaning of multiplication.
   * @param a The first operand.
   * @param b The second operand.
   * @return The result of the multiplication.
   */
  def mul(a: T, b: T): T
}

package me.yuhuan.reparo.witness

/**
 * Represents a semiring.
 *
 * A semiring is a [[lea.algebra.Ring Ring]] in which the set under operation `add` forms a
 * monoid instead of an abelian group (i.e., no requirement for  the additive inverses).
 *
 * @tparam T The type of the elements in the semiring.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait Semiring[T] extends AdditiveMonoid[T] with MultiplicativeMonoid[T]

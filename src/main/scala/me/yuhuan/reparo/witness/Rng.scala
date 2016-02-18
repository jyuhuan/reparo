package me.yuhuan.reparo.witness

/**
 * Represents a rng.
 *
 * A rng is a [[lea.algebra.Ring Ring]] without multiplicative identity (hence the lack of
 * ''i'' in its name).
 *
 * @tparam T The type of the elements in the rng.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 */
trait Rng[T] extends AdditiveGroup[T] with MultiplicativeSemigroup[T]
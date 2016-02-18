package me.yuhuan.reparo.witness

/**
 * Represents a ring.
 *
 * A ring has two abstract operations: `add` and `mul`. The set under operation `add` forms an
 * abelian group, while the set under operation `mul` forms a monoid.
 *
 * @tparam T The type of the elements in the ring.
 *
 * @author Yuhuan Jiang
 * @version 1.0
 * @since 1.0
 *
 */
trait Ring[T] extends AdditiveGroup[T] with MultiplicativeMonoid[T]